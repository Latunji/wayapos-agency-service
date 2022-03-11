package com.example.agentservice.controller;

import com.example.agentservice.dto.AssignDto;
import com.example.agentservice.dto.MerchantDto;
import com.example.agentservice.dto.ViewDto;
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
    public ResponseEntity<Response> registerMerchant(@RequestHeader("Authorization") String authHeader, @RequestBody MerchantDto request){
        try {
            return new ResponseEntity<>(merchantService.registerMerchant(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(UPDATE_MERCHANT)
    public ResponseEntity<Response> updateMerchant(@RequestHeader("Authorization") String authHeader, @RequestBody MerchantDto request){
        try {
            return new ResponseEntity<>(merchantService.updateMerchant(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(VIEW_MERCHANT_BY_ID)
    public ResponseEntity<Response> viewMerchantById(@RequestHeader("Authorization") String authHeader, @RequestBody Long request){
        try {
            return new ResponseEntity<>(merchantService.viewMerchantById(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(VIEW_ALL_MERCHANTS)
    public ResponseEntity<Response> viewAllMerchants(@RequestHeader("Authorization") String authHeader, @RequestBody ViewDto request){
        try {
            return new ResponseEntity<>(merchantService.viewAllMerchants(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(VIEW_ALL_MERCHANTS_BY_USERID)
    public ResponseEntity<Response> viewAllMerchantsByUserId(@RequestHeader("Authorization") String authHeader, @RequestBody ViewDto request){
        try {
            return new ResponseEntity<>(merchantService.viewAllMerchantsByUserId(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(DELETE_MERCHANT)
    public ResponseEntity<Response> deleteMerchant(@RequestHeader("Authorization") String authHeader, @RequestBody Long request){
        try {
            return new ResponseEntity<>(merchantService.deleteMerchant(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(GET_UNASSIGNED_TERMINALS)
    public ResponseEntity<Response> getUnAssignedTerminals(@RequestHeader("Authorization") String authHeader){
        try {
            return new ResponseEntity<>(merchantService.getUnAssignedTerminals(authHeader), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(ASSIGN_TERMINAL)
    public ResponseEntity<Response> assignTerminalsToMerchantsr(@RequestHeader("Authorization") String authHeader, @RequestBody AssignDto request){
        try {
            return new ResponseEntity<>(merchantService.assignTerminalsToMerchantsr(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(UNASSIGN_TERMINAL)
    public ResponseEntity<Response> unassignTerminals(@RequestHeader("Authorization") String authHeader, @RequestBody AssignDto request){
        try {
            return new ResponseEntity<>(merchantService.unassignTerminals(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(GET_ALL_TERMINALS_BY_MERCHANT)
    public ResponseEntity<Response> getAllTerminalsByMerchant(@RequestHeader("Authorization") String authHeader, @RequestBody ViewDto request){
        try {
            return new ResponseEntity<>(merchantService.getAllTerminalsByMerchant(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(ACTIVATE_MERCHANT)
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



    @RequestMapping(
            value = "/**",
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }



}
