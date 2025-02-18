package com.example.agentservice.service.impl;

import com.example.agentservice.constants.PricingStatus;
import com.example.agentservice.dto.*;
import com.example.agentservice.model.Merchants;
import com.example.agentservice.model.Pricing;
import com.example.agentservice.model.Terminal;
import com.example.agentservice.model.User;
import com.example.agentservice.repository.MerchantRepository;
import com.example.agentservice.repository.PricingRepository;
import com.example.agentservice.repository.TerminalRepository;
import com.example.agentservice.repository.WayaPosUsersRepository;
import com.example.agentservice.service.MerchantService;
import com.example.agentservice.util.Response;
import com.example.agentservice.util.RestCall;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static com.example.agentservice.constants.Constants.*;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class MerchantServiceImpl implements MerchantService {
    ExecutorService executors = Executors.newCachedThreadPool();
    @Value("${user.auth.endpoint}")
    String url;
    @Value("${createmerchanturl}")
    String createMerchantUrl;
    @Value("${walletbalanceurl}")
    String walletBalanceUrl;
    @Value("${user.addbank}")
    String addBankUrl;

    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;
    private final MerchantRepository merchantRepository;
    private final UserService userService;
    private final TerminalRepository terminalRepository;
    private final LogService logService;
    private final WayaPosUsersRepository wayaPosUsersRepository;
    private final PricingRepository pricingRepository;
    Response response = new Response();
    private final Gson gson;

    @Value("${kyc.endpoint.createkyc}")
    String createKyc;

    @Value("${kyc.endpoint.addkyc}")
    String addKyc;

    @Autowired
    private RestCall restCall;
    @Override
    public CreateMerchantResponseDTO registerMerchant(MerchantDto merchantDto) throws Exception {

        Merchants merchants = Merchants.builder()
                .firstname(merchantDto.getFirstName())
                .surname(merchantDto.getSurname())
                .email(merchantDto.getEmail())
                .dob(merchantDto.getDateOfBirth())
                .gender(merchantDto.getGender())
                .phoneNumber("234"+merchantDto.getPhoneNumber().substring(1))
                .state(merchantDto.getState())
                .merchantCategoryCode(merchantDto.getMerchantCategoryCode())
                .merchantNameAndLocation(buildF43(merchantDto.getFirstName(),merchantDto.getSurname(),"LANG"))
                .countryCode("566")
                .city("566")
                .currencyCode(merchantDto.getCurrencyCode())
                .createdAt(new Date())
                .acquiringInstitutionID(merchantDto.getAcquiringInstitutionCode())
                .admin(merchantDto.isAdmin())
                .businessType(merchantDto.getBusinessType())
                .orgName(merchantDto.getOrgName())
                .orgType(merchantDto.getOrgType())
                .officeAddress(merchantDto.getOfficeAddress())
                .referenceCode(merchantDto.getReferenceCode())
                .orgPhone("234"+merchantDto.getOrgPhone().substring(1))
                .settlementType(SettlementType.INSTANT)
                .build();

        Merchants merchants1 = merchantRepository.findByEmail(merchants.getEmail()).orElse(null);
        if (merchants1!=null){
            log.error("merchant ID already exist for {} ",merchants);
            return CreateMerchantResponseDTO.builder()
                    .timeStamp(new Date().getTime())
                    .message("Failed")
                    .status(false)
                    .data("merchant already exist with name "+ merchantDto.getEmail())
                    .build();
        }

        CreateMerchantRequestDTO requestDTO = new CreateMerchantRequestDTO();
        modelMapper.map(merchantDto,requestDTO);
        requestDTO.setPhoneNumber(merchants.getPhoneNumber());
        requestDTO.setOrgPhone(merchants.getOrgPhone());

        CreateMerchantResponseDTO responseDTO = userService.createMerchant(createMerchantUrl,requestDTO);
        if (responseDTO.isStatus()){
            Short num = 0;
            String uid = String.valueOf(new Date().getTime());
            merchants.setMerchantId("00"+uid);
            merchants.setActive(Boolean.FALSE);
            merchants.setDeleted(num);
            merchants.setModifiedAt(new Date());

            Merchants save = merchantRepository.save(merchants);

            executors.execute(() ->logService.sendLogs(AuditDto.builder()
                    .userID(save.getUserId())
                    .activity(save.getFirstname()+" registered a merchant "+"Name:"+
                            merchants.getFirstname()+" ID: "+merchants.getMerchantId())
                    .build()) );

            //todo: create default pricing for merchant
            createDefaultPricing(save.getUserId(),save.getOrgName(),"CARD");
        }
        return responseDTO;
    }

    @Override
    public Response updateMerchant(String authHeader, MerchantUpdateDto merchantDto) throws IOException {
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        String loggedInEmail = user.getData().getEmail();
        Merchants merchants = merchantRepository.findByEmail(loggedInEmail).orElse(null);
        if (merchants == null){
            log.error("merchant with ID {} not found ",user.getData().getEmail());
            return new Response(FAILED_CODE,FAILED,"Merchant with email "+user.getData().getEmail()+ " not found");
        }

        //createKycForCustomer
        CreateKycDto createKycDto = new CreateKycDto();
        createKycDto.setCustomerEmail(merchants.getEmail());
        createKycDto.setCustomerName(merchants.getFirstname() +" "+merchants.getSurname());
        createKycDto.setCustomerPhoneNumber(merchants.getPhoneNumber());
        createKycDto.setCustomerId(Long.valueOf(user.getData().getId()));

        log.info("creating kyc for user..........");

        JSONObject jsonObject = new JSONObject(restCall.executeRequest(authHeader, createKycDto, createKyc));
        boolean stat = Boolean.valueOf(jsonObject.get("status").toString());
        if(stat) {
            merchants.setFirstname(merchantDto.getFirstName());
            merchants.setSurname(merchantDto.getSurname());
            merchants.setDob(merchantDto.getDateOfBirth());
            merchants.setGender(merchantDto.getGender());
            merchants.setCity(merchantDto.getCity());
            merchants.setPhoneNumber(merchantDto.getPhoneNumber());
            merchants.setOfficeAddress(merchantDto.getOfficeAddress());
            merchants.setModifiedAt(new Date());
            merchants.setUserId(user.getData().getId());
            merchants.setActive(Boolean.TRUE);
            merchants.setUpdateFlag(Boolean.TRUE);
            if(Objects.isNull(merchants.getSettlementType()))
                merchants.setSettlementType(SettlementType.INSTANT);

            Merchants save = merchantRepository.save(merchants);

            executors.submit(() ->logService.sendLogs(AuditDto.builder()
                    .userID(user.getData().getId())
                    .activity(user.getData().getFirstName()+" updated a merchant "+"Name:"+
                            save.getFirstname()+" ID: "+save.getMerchantId())
                    .build()) );

            return new Response(SUCCESS_CODE,SUCCESS,save);
        }
        return new Response(FAILED_CODE,FAILED,"Kyc Couldn't Be Created");
    }


    @Override
    public Response checkUpdateFlag(String token, String userId){
        User user = userService.validateUser(token);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }
        Merchants merchants = merchantRepository.findByUserId(userId).orElse(null);
        if(merchants == null){
            log.error("merchant not found for id {}",merchants);
            return new Response(FAILED_CODE,FAILED,"merchants not found for id "+userId);
        }
        return new Response(SUCCESS_CODE,SUCCESS, merchants.getUpdateFlag());

    }

    @Override
    public Response viewMerchantById(String authHeader, Long merchantId) {
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        Merchants merchants = merchantRepository.findById(merchantId).orElse(null);
        if (merchants==null){
            log.error("merchant not found for id {}",merchants);
            return new Response(FAILED_CODE,FAILED,"merchants not found for id "+merchantId);
        }
        log.info("merchant gotten for ID {} is {}",merchantId,merchants);
        executors.submit(() ->logService.sendLogs(AuditDto.builder()
                .userID(user.getData().getId())
                .activity(user.getData().getFirstName()+" viewd merchants "+"Name:"+
                        merchants.getFirstname()+" ID: "+merchants.getMerchantId())
                .build()) );
        return new Response(SUCCESS_CODE,SUCCESS,merchants);
    }

    @Override
    public Response updateSettlementAccount(String token, SettlementDto settlementDto) {
        User user = userService.validateUser(token);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        Merchants merchants = merchantRepository.findByUserId(settlementDto.getUserId()).orElse(null);
        if(merchants == null){
            return new Response(FAILED_CODE, FAILED, "Merchant cannot be found");
        }
        merchants.setSettlementType(settlementDto.getSettlementType());
        merchantRepository.save(merchants);

        return new Response(SUCCESS_CODE, SUCCESS, "Settlement Type Updated");
    }


    @Override
    public Response addBankAccount(String token, BankAccountDto bankAccountDto) {
        User user = userService.validateUser(token);
        JSONObject jsonObject = new JSONObject();
        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        Merchants merchants = merchantRepository.findByUserId(bankAccountDto.getUserId()).orElse(null);
        if(merchants == null){
            return new Response(FAILED_CODE, FAILED, "Merchant cannot be found");
        }
        try {
            jsonObject = new JSONObject(restCall.addBanks(token, bankAccountDto, addBankUrl));
        }
        catch (Exception ex){
          return new Response(FAILED_CODE, FAILED, "Error occured adding Bank...."+ex.getMessage());
        }
        if(jsonObject.get("data").toString() == "true"){
            return  new Response(SUCCESS_CODE, SUCCESS, "Bank Account Added Successfully");
        }else{
            return new Response(FAILED_CODE, FAILED, "Bank Account Could Not Be Added");
        }

    }

    @Override
    public Response searchMerchant(String authHeader, SearchDto searchDto) {

        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        List<Merchants> merchants = merchantRepository.findAllByEmail(searchDto.getEmail());

        if(merchants.isEmpty()){
            log.error("no merchant found {}",merchants);
            return new Response(FAILED_CODE,FAILED,"merchants found for "+merchants);
        }
        return new Response(SUCCESS_CODE,SUCCESS,merchants);
    }

    @Override
    public Response viewMerchantByMerchantId(String authHeader, String merchantId) {
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        Merchants merchants = merchantRepository.findByMerchantId(merchantId).orElse(null);
        if (merchants==null){
            log.error("merchant not found for id {}",merchants);
            return new Response(FAILED_CODE,FAILED,"merchants not found for Merchant id "+merchantId);
        }
        log.info("merchant gotten for UserID {} is {}",merchantId,merchants);
        executors.submit(() ->logService.sendLogs(AuditDto.builder()
                .userID(user.getData().getId())
                .activity(user.getData().getFirstName()+" viewd merchants "+"Name:"+
                        merchants.getFirstname()+" ID: "+merchants.getMerchantId())
                .build()) );
        return new Response(SUCCESS_CODE,SUCCESS,merchants);
    }

    @Override
    public Response viewMerchantByUserId(String authHeader, String userId) {
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        Merchants merchants = merchantRepository.findByUserId(userId).orElse(null);
        if (merchants==null){
            log.error("merchant not found for id {}",merchants);
            return new Response(FAILED_CODE,FAILED,"merchants not found for User id "+userId);
        }
        log.info("merchant gotten for UserID {} is {}",userId,merchants);
        executors.submit(() ->logService.sendLogs(AuditDto.builder()
                .userID(user.getData().getId())
                .activity(user.getData().getFirstName()+" viewd merchants "+"Name:"+
                        merchants.getFirstname()+" ID: "+merchants.getMerchantId())
                .build()) );
        return new Response(SUCCESS_CODE,SUCCESS,merchants);
    }

    @Override
    public Response viewAllMerchants(String authHeader, ViewDto viewDto) {
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }
        Pageable pageable = PageRequest.of(viewDto.getPageNo(),viewDto.getPageSize());



        Page<Merchants> merchantsPage = merchantRepository.findAll( pageable);
        log.info("view all merchants by superadmin result is {}",merchantsPage);
        executors.submit(() ->logService.sendLogs(AuditDto.builder()
                .userID(user.getData().getId())
                .activity(user.getData().getFirstName()+" viewed all merchants")
                .build()) );
        return new Response(SUCCESS_CODE,SUCCESS,merchantsPage);
    }

    @Override
    public Response viewAllMerchantsByUserId(String authHeader, ViewDto viewDto) {
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }
        Merchants merchants = new Merchants();
        Pageable pageable = PageRequest.of(viewDto.getPageNo(),viewDto.getPageSize());
        if (viewDto.getParams().containsKey("merchantId"))
            merchants.setMerchantId((String) viewDto.getParams().get("merchantId"));
        if (viewDto.getParams().containsKey("firstname"))
            merchants.setFirstname((String) viewDto.getParams().get("firstname"));
        if (viewDto.getParams().containsKey("email"))
            merchants.setEmail((String) viewDto.getParams().get("email"));
        if (viewDto.getParams().containsKey("email"))
            merchants.setEmail((String) viewDto.getParams().get("email"));

        merchants.setUserId(user.getData().getId());

        Page<Merchants> merchantsPage = merchantRepository.findAll(Example.of(merchants), pageable);
        log.info("view all merchants by user result is {}",merchantsPage);
        executors.submit(() ->logService.sendLogs(AuditDto.builder()
                .userID(user.getData().getId())
                .activity(user.getData().getFirstName()+" viewed all merchants for merchant ")
                .build()) );
        return new Response(SUCCESS_CODE,SUCCESS,merchantsPage);
    }

    @Override
    public Response deleteMerchant(String authHeader, Long merchantId) {
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        Merchants merchants = merchantRepository.findById(merchantId).orElse(null);
        if (merchants==null){
            log.error("merchant not found for id {}",merchants);
            return new Response(FAILED_CODE,FAILED,"merchants not found for id "+merchantId);
        }
        log.info("merchant about to be deleted {}",merchants);
        executors.submit(() ->logService.sendLogs(AuditDto.builder()
                .userID(user.getData().getId())
                .activity(user.getData().getFirstName()+" deleted merchant  "+merchants.getFirstname())
                .build()) );
        merchantRepository.delete(merchants);
        log.info("merchant successfully deleted");
        return new Response(SUCCESS_CODE,SUCCESS,"merchant successfully deleted for ID "+merchantId);
    }

    @Override
    public Response getUnAssignedTerminals(String authHeader) {
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        List<Terminal> byMerchantsIsNull = terminalRepository.findAllByMerchantsIsNullAndMerchants_UserId(user.getData().getId());
        log.info("List of unassigned terminals {}",byMerchantsIsNull);
        executors.submit(() ->logService.sendLogs(AuditDto.builder()
                .userID(user.getData().getId())
                .activity(user.getData().getFirstName()+" fetched all unassigned terminals ")
                .build()) );
        return new Response(SUCCESS_CODE,SUCCESS,byMerchantsIsNull);
    }

    @Override
    public Response getAllTerminalsByMerchant(String authHeader, ViewDto viewDto) {
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }
        Long id = Long.parseLong((String) viewDto.getParams().get("id"));


        Merchants merchants = merchantRepository.findById(id).orElse(null);
        if (merchants==null){
            log.info("merchants cannot be null for id {}",id);
            return new Response(FAILED_CODE,FAILED,"Merchant id cannot be null");
        }

        List<Terminal> byMerchants_idAndUserId = terminalRepository.findByMerchants_IdAndMerchantsUserId(merchants.getId(), user.getData().getId());
        log.info("Response gotten is {}",byMerchants_idAndUserId);
        executors.submit(() ->logService.sendLogs(AuditDto.builder()
                .userID(user.getData().getId())
                .activity(user.getData().getFirstName()+" fetched all terminals for merchant "+merchants.getFirstname())
                .build()) );
        return new Response(SUCCESS_CODE,SUCCESS,byMerchants_idAndUserId);
    }

    @Override
    public Response activateMerchant(String authHeader, String merchantId) {
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        merchantId = merchantId.replace("\"","");
        
        Merchants merchants = merchantRepository.findByMerchantIdAndUserId(merchantId,user.getData().getId()).orElse(null);
        if (merchants==null){
            log.error("emrchants not found for merchant id {} and user id {}",merchantId,user.getData().getId());
            return new Response(FAILED_CODE,FAILED,"merchant not found for merchant id "+merchantId +" and userid "+user.getData().getId());
        }
        
        log.info("merchant gotten {}",merchants);
        merchants.setActive(true);
        Merchants save = merchantRepository.save(merchants);
        log.info("merchant activated {}",save);
        executors.submit(() ->logService.sendLogs(AuditDto.builder()
                .userID(user.getData().getId())
                .activity(user.getData().getFirstName()+" Activated merchantt "+merchants.getFirstname())
                .build()) );
        return new Response(SUCCESS_CODE,SUCCESS,save);
    }

    @Override
    public Response deactivateMerchant(String authHeader, String merchantId) {
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }
        merchantId = merchantId.replace("\"","");
        Merchants merchants = merchantRepository.findByMerchantIdAndUserId(merchantId,user.getData().getId()).orElse(null);
        if (merchants==null){
            log.error("emrchants not found for merchant id {} and user id {}",merchantId,user.getData().getId());
            return new Response(FAILED_CODE,FAILED,"merchant not found for merchant id "+merchantId +" and userid "+user.getData().getId());
        }

        log.info("merchant gotten {}",merchants);
        merchants.setActive(false);
        Merchants save = merchantRepository.save(merchants);
        log.info("merchant activated {}",save);
        executors.submit(() ->logService.sendLogs(AuditDto.builder()
                .userID(user.getData().getId())
                .activity(user.getData().getFirstName()+" Deactivated merchantt "+merchants.getFirstname())
                .build()) );
        return new Response(SUCCESS_CODE,SUCCESS,save);
    }

    @Override
    public Response getAllMerchants(String authHeader) {
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        List<Merchants> merchants = merchantRepository.findAll();
        return new Response(SUCCESS_CODE,SUCCESS,merchants);

    }

    @Override
    public Response getByAdminType(String authHeader, boolean isAdmin) {
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        List<Merchants> merchants = merchantRepository.findByAdmin(isAdmin);
        return new Response(SUCCESS_CODE,SUCCESS,merchants);    }



    @Override
    public Response getMerchantBalance(String authHeader, String userID) {
        User user = userService.validateUser(authHeader);
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }
        String url = walletBalanceUrl.concat("/").concat(userID);
        WalletBalanceStreamedResponse response = userService.getMerchantBalance(url,authHeader);

        log.info("wallet balance is {}}",response.toString());

        if (response==null){
            log.info("wallet balance is null for account {}",userID);
            return new Response(FAILED_CODE,FAILED,"Balance not gotten");

        }

        return new Response(SUCCESS_CODE,SUCCESS,response);
    }

    @Override
    public Response updateMerchantUserID(UpdateMerchantIDRequest request) {
        log.info("About updateing merchant user id");

        Merchants merchants = merchantRepository.findByEmail(request.getEmail()).orElse(null);
        if (merchants==null){
            return new Response(FAILED_CODE,FAILED,"merchant not found");

        }

        else{
            if (merchants.getUserId() == null ||merchants.getUserId().isEmpty()|| merchants.getUserId().equals(""))
                merchants.setUserId(request.getUserID());
            log.info("user id updated for merchant");
            merchantRepository.save(merchants);
            return new Response(SUCCESS_CODE,SUCCESS,"merchant user id updated successfuly");

        }


    }


    @Override
    public Response getMerchantUserMetrics(String token) {
        try {
            User user = userService.validateUser(token);
            if(Objects.isNull(user)){
                return  new Response(FAILED_CODE,FAILED,"Validation failed");
            }
            UserMetrics metrics = new UserMetrics();
            metrics.setActiveUser(merchantRepository.countByActive(Boolean.TRUE));
            metrics.setInActiveUser(merchantRepository.countByActive(Boolean.FALSE));
            metrics.setActiveAdminUser(merchantRepository.countByActiveAndAdmin(Boolean.TRUE,Boolean.TRUE));
            metrics.setInActiveAdminUser(merchantRepository.countByActiveAndAdmin(Boolean.FALSE,Boolean.FALSE));
            metrics.setTotalUser(merchantRepository.count());

            return  new Response(SUCCESS_CODE,SUCCESS,metrics);
        }catch (Exception ex){
            log.info("An err: {} ",ex.getMessage());
            return  new Response(FAILED_CODE,FAILED,"An error occurred, try again later");
        }
    }

    public static String padright(String s, int len, char c) throws Exception {
        s = s.trim();
        if (s.length() > len)
            throw new Exception("invalid len " + s.length() + "/" + len);
        StringBuilder d = new StringBuilder(len);
        int fill = len - s.length();
        d.append(s);
        while (fill-- > 0)
            d.append(c);
        return d.toString();
    }
    private static String buildF43(String firstName, String lastName, String country) throws Exception {
        StringBuilder sb = new StringBuilder(43);
        String merchantBusinessName = padright(firstName+lastName,23,' ');
        String city = padright("LAGOS",13,' ');

        return sb.append(merchantBusinessName).append(city).append(country).toString();
    }


    private void createDefaultPricing(String userId, String name, String productType){
        Pricing pricing = new Pricing();
        pricing.setCreatedAt(new Date());
        pricing.setPricingRate(new BigDecimal(1.50));
        pricing.setAggregator(true);
        pricing.setAgent(true);
        pricing.setCreatedBy(userId);
        pricing.setCap(new BigDecimal(2000));
        pricing.setDeleted(false);
        pricing.setDiscount(new BigDecimal(1.50));
        pricing.setMerchant(true);
        pricing.setProducts(productType);
        pricing.setName(name);
        pricing.setUpdatedAt(new Date());
        pricing.setUserId(userId);
        pricing.setStatus(PricingStatus.DEFAULT);
        pricingRepository.save(pricing);
    }

}
