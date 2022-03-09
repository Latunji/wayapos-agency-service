package com.example.agentservice.service;

import com.example.agentservice.dto.AssignDto;
import com.example.agentservice.dto.MerchantDto;
import com.example.agentservice.dto.ViewDto;
import com.example.agentservice.model.Merchants;
import com.example.agentservice.model.Terminal;
import com.example.agentservice.model.User;
import com.example.agentservice.repository.MerchantRepository;
import com.example.agentservice.repository.TerminalRepository;
import com.example.agentservice.util.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

import static com.example.agentservice.constants.Constants.*;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class MerchantServiceImpl implements MerchantService {
    @Value("${user.auth.endpoint}")
    String url;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;
    private final MerchantRepository merchantRepository;
    private final UserService userService;
    private final TerminalRepository terminalRepository;
    Response response = new Response();
    @Override
    public Response registerMerchant(String authHeader, MerchantDto merchantDto) {
        log.info("About validating user token with auth endpoint {}",url);
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        Merchants merchants = Merchants.builder()
                .merchantId(merchantDto.getMerchantId())
                .firstname(merchantDto.getFirstname())
                .surname(merchantDto.getSurname())
                .email(merchantDto.getEmail())
                .dob(merchantDto.getDob())
                .gender(merchantDto.getGender())
                .address(merchantDto.getAddress())
                .phoneNumber(merchantDto.getPhoneNumber())
                .state(merchantDto.getState())
                .merchantCategoryCode(merchantDto.getMerchantCategoryCode())
                .merchantNameAndLocation(merchantDto.getMerchantNameAndLocation())
                .countryCode(merchantDto.getCountryCode())
                .city(merchantDto.getCity())
                .currencyCode(merchantDto.getCurrencyCode())
                .createdAt(new Date())
                .acquiringInstitutionID(merchantDto.getAcquiringInstitutionID())
                .userId(user.getData().getId())
                .build();


        Merchants merchants1 = merchantRepository.findByMerchantIdAndUserId(merchants.getMerchantId(),user.getData().getId()).orElse(null);
        if (merchants1!=null){
            log.error("merchant ID already exist for {} ",merchants);
            return new Response(FAILED_CODE,FAILED,"merchant already exists with id "+merchants.getMerchantId());
        }


        Merchants save = merchantRepository.save(merchants);

        log.info("merchants saved successfully {}",save);
        return new Response(SUCCESS_CODE,SUCCESS,save);
    }

    @Override
    public Response updateMerchant(String authHeader, MerchantDto merchantDto) {
        log.info("About validating user token with auth endpoint {}",url);
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        Merchants merchants = merchantRepository.findByMerchantIdAndUserId(merchantDto.getMerchantId(),user.getData().getId()).orElse(null);
        if (merchants==null){
            log.error("merchant with ID {} not found ",merchantDto.getMerchantId());
            return new Response(FAILED_CODE,FAILED,"Merchant with id "+merchantDto.getMerchantId()+ " not found");
        }
        log.info("merchant gotten {} ",merchants);
        modelMapper.map(merchantDto,merchants);
        merchants.setModifiedAt(new Date());
        log.info("Merchant updated to {}",merchants);
        Merchants save = merchantRepository.save(merchants);
        return new Response(SUCCESS_CODE,SUCCESS,save);
    }

    @Override
    public Response viewMerchantById(String authHeader, Long merchantId) {
        log.info("About validating user token with auth endpoint {}",url);
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
        return new Response(SUCCESS_CODE,SUCCESS,merchants);
    }

    @Override
    public Response viewAllMerchants(String authHeader, ViewDto viewDto) {
        log.info("About validating user token with auth endpoint {}",url);
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }
        Pageable pageable = PageRequest.of(viewDto.getPageNo(),viewDto.getPageSize());



        Page<Merchants> merchantsPage = merchantRepository.findAll( pageable);
        log.info("view all merchants by superadmin result is {}",merchantsPage);

        return new Response(SUCCESS_CODE,SUCCESS,merchantsPage);
    }

    @Override
    public Response viewAllMerchantsByUserId(String authHeader, ViewDto viewDto) {
        log.info("About validating user token with auth endpoint {}",url);
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

        return new Response(SUCCESS_CODE,SUCCESS,merchantsPage);
    }

    @Override
    public Response deleteMerchant(String authHeader, Long merchantId) {
        log.info("About validating user token with auth endpoint {}",url);
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
        merchantRepository.delete(merchants);
        log.info("merchant successfully deleted");
        return new Response(SUCCESS_CODE,SUCCESS,"merchant successfully deleted for ID "+merchantId);    }

    @Override
    public Response getUnAssignedTerminals(String authHeader) {
        log.info("About validating user token with auth endpoint {}",url);
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        List<Terminal> byMerchantsIsNull = terminalRepository.findByMerchantsIsNullAndUserId(user.getData().getId());
        log.info("List of unassigned terminals {}",byMerchantsIsNull);

        return new Response(SUCCESS_CODE,SUCCESS,byMerchantsIsNull);
    }

    @Override
    public Response assignTerminalsToMerchantsr(String authHeader, AssignDto assignDto) {
        log.info("About validating user token with auth endpoint {}",url);
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        Merchants merchants = merchantRepository.findById(assignDto.getMerchantID()).orElse(null);
        if (merchants==null){
            log.error("Merchant not found for id {}",assignDto.getMerchantID());
            return new Response(FAILED_CODE,FAILED, "Merchant not found for id "+assignDto.getMerchantID());
        }

        List<Terminal> res = new ArrayList<>();
        for (Long id : assignDto.getTerminalIds()){
            Terminal terminal = terminalRepository.findById(id).orElse(null);
            if (terminal==null){
                return new Response(FAILED_CODE,FAILED,"Ensure all terminals are not mapped. check id "+id);
            }
            else if (!terminal.getUserId().equals(user.getData().getId())){
                return new Response(FAILED_CODE,FAILED,"Ensure terminal is issued to uderid  "+user.getData().getId());

            }
            else if (terminal.getMerchants()!=null){
                return new Response(FAILED_CODE,FAILED,"terminal already mapped  ");

            }
            else {
                terminal.setMerchants(merchants);
                res.add(terminal);
            }


        }
        log.info("about mapping {}",res);
        List<Terminal> terminals = terminalRepository.saveAll(res);
        log.info("terminals successfully mapped {}",terminals);

        return new Response(SUCCESS_CODE,SUCCESS,terminals);
    }

    @Override
    public Response unassignTerminals(String authHeader, AssignDto assignDto) {
        log.info("About validating user token with auth endpoint {}",url);
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        Merchants merchants = merchantRepository.findById(assignDto.getMerchantID()).orElse(null);
        if (merchants==null){
            log.error("Merchant not found for id {}",assignDto.getMerchantID());
            return new Response(FAILED_CODE,FAILED, "Merchant not found for id "+assignDto.getMerchantID());
        }

        List<Terminal> res = new ArrayList<>();
        for (Long id : assignDto.getTerminalIds()){
            Terminal terminal = terminalRepository.findByIdAndMerchantsNotNullAndUserId(id,user.getData().getId());
            if (terminal==null){
                return new Response(FAILED_CODE,FAILED,"Ensure all terminals are not mapped. check id "+id);
            }
            else {
                terminal.setMerchants(null);
                res.add(terminal);
            }


        }
        log.info("about unmapping {}",res);
        List<Terminal> terminals = terminalRepository.saveAll(res);
        log.info("terminals successfully unmapped {}",terminals);

        return new Response(SUCCESS_CODE,SUCCESS,terminals);
    }

    @Override
    public Response getAllTerminalsByMerchant(String authHeader, ViewDto viewDto) {
        log.info("About validating user token with auth endpoint {}",url);
        User user = userService.validateUser(authHeader);

        //validate user is not null
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        Merchants merchants = merchantRepository.findById((Long) viewDto.getParams().get("id")).orElse(null);
        if (merchants==null){
            log.info("merchants cannot be null for id {}",viewDto.getParams().get("id"));
            return new Response(FAILED_CODE,FAILED,"Merchant id cannot be null");
        }

        Pageable pageable = PageRequest.of(viewDto.getPageNo(),viewDto.getPageSize());

        Page<Terminal> byMerchants_idAndUserId = terminalRepository.findByMerchants_IdAndUserId(merchants.getId(), user.getData().getId(), pageable);
        log.info("Response gotten is {}",byMerchants_idAndUserId);

        return new Response(SUCCESS_CODE,SUCCESS,byMerchants_idAndUserId);
    }
}
