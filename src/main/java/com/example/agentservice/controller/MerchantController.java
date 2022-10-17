package com.example.agentservice.controller;

import com.example.agentservice.dto.*;
import com.example.agentservice.service.MerchantService;
import com.example.agentservice.util.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.agentservice.constants.Constants.*;

@RestController @Slf4j @RequiredArgsConstructor
@RequestMapping(PATH) @CrossOrigin
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping(REGISTER_MERCHANT)
    public ResponseEntity<CreateMerchantResponseDTO> registerMerchant(@RequestBody MerchantDto request){
        try {
            return new ResponseEntity<>(merchantService.registerMerchant(request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(UPDATE_SETTLEMENT_TYPE)
    public ResponseEntity<Response> updateSettlementType(@RequestHeader("Authorization") String authHeader, @RequestBody SettlementDto request){
        try {
            return new ResponseEntity<Response>(merchantService.updateSettlementAccount(authHeader, request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(ADD_BANK_ACCOUNT)
    public ResponseEntity<Response> addBankAccount(@RequestHeader("Authorization") String authHeader, @RequestBody BankAccountDto request){
        try {
            return new ResponseEntity<Response>(merchantService.addBankAccount(authHeader, request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping(SEARCH_MERCHANT)
    public ResponseEntity<Response> searchMerchant(@RequestHeader("Authorization") String authHeader,
                                                                    @RequestBody SearchDto request){
        try {
            return new ResponseEntity<>(merchantService.searchMerchant(authHeader, request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("updateMerchantUserID")
    public ResponseEntity<Response> updateMerchantUserID(@RequestBody UpdateMerchantIDRequest request){
        try {
            return new ResponseEntity<>(merchantService.updateMerchantUserID(request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping(UPDATE_MERCHANT)
    public ResponseEntity<Response> updateMerchant(@RequestHeader("Authorization") String authHeader, @RequestBody MerchantUpdateDto request){
        try {
            return new ResponseEntity<>(merchantService.updateMerchant(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(VIEW_MERCHANT_BY_ID)
    public ResponseEntity<Response> viewMerchantById(@RequestHeader("Authorization") String authHeader, @RequestBody Long request){
        try {
            return new ResponseEntity<>(merchantService.viewMerchantById(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping(VIEW_MERCHANT_BY_MERCHANT_ID)
    public ResponseEntity<Response> viewMerchantByMerchantId(@RequestHeader("Authorization") String authHeader, @RequestBody String merchantId){
        try {
            return new ResponseEntity<>(merchantService.viewMerchantByMerchantId(authHeader,merchantId), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(VIEW_MERCHANT_BY_USERID)
    public ResponseEntity<Response> viewMerchantByUserId(@RequestHeader("Authorization") String authHeader, @RequestBody String userId) {
        try {
            return new ResponseEntity<>(merchantService.viewMerchantByUserId(authHeader, userId), HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error is {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }


            @PostMapping(VIEW_ALL_MERCHANTS)
    public ResponseEntity<Response> viewAllMerchants(@RequestHeader("Authorization") String authHeader, @RequestBody ViewDto request){
        try {
            return new ResponseEntity<>(merchantService.viewAllMerchants(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(VIEW_ALL_MERCHANTS_BY_USERID)
    public ResponseEntity<Response> viewAllMerchantsByUserId(@RequestHeader("Authorization") String authHeader, @RequestBody ViewDto request){
        try {
            return new ResponseEntity<>(merchantService.viewAllMerchantsByUserId(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(DELETE_MERCHANT)
    public ResponseEntity<Response> deleteMerchant(@RequestHeader("Authorization") String authHeader, @RequestBody Long request){
        try {
            return new ResponseEntity<>(merchantService.deleteMerchant(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(GET_UNASSIGNED_TERMINALS)
    public ResponseEntity<Response> getUnAssignedTerminals(@RequestHeader("Authorization") String authHeader){
        try {
            return new ResponseEntity<>(merchantService.getUnAssignedTerminals(authHeader), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(GET_ALL_TERMINALS_BY_MERCHANT)
    public ResponseEntity<Response> getAllTerminalsByMerchant(@RequestHeader("Authorization") String authHeader, @RequestBody ViewDto request){
        try {
            return new ResponseEntity<>(merchantService.getAllTerminalsByMerchant(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*@PostMapping(ACTIVATE_MERCHANT)
    public ResponseEntity<Response> activateMerchant(@RequestHeader("Authorization") String authHeader, @RequestBody String merchantId){
        try {
            return new ResponseEntity<>(merchantService.activateMerchant(authHeader,merchantId), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(DEACTIVATE_MERCHANT)
    public ResponseEntity<Response> deactivateMerchant(@RequestHeader("Authorization") String authHeader, @RequestBody String merchantId){
        try {
            return new ResponseEntity<>(merchantService.deactivateMerchant(authHeader,merchantId), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
*/
    @GetMapping("getallmerchants")
    public ResponseEntity<Response> getAllMerchants(@RequestHeader("Authorization") String authHeader){
        try {
            return new ResponseEntity<>(merchantService.getAllMerchants(authHeader), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("getByAdminType")
    public ResponseEntity<Response> getByAdminType(@RequestHeader("Authorization") String authHeader,@RequestBody boolean request){
        try {
            return new ResponseEntity<>(merchantService.getByAdminType(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getMerchantBalance/{userID}")
    public ResponseEntity<Response> getMerchantBalance(@RequestHeader("Authorization") String authHeader,@PathVariable("userID") String userID){
        try {
            return new ResponseEntity<>(merchantService.getMerchantBalance(authHeader,userID), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(path = "/metrics")
    public ResponseEntity<Response> findMerchantMetrics(
            @RequestHeader("Authorization") String authHeader){
        try {
            Response response = merchantService.getMerchantUserMetrics(authHeader);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {} ",e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(
            value = "/**",
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }



}
