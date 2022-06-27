package com.example.agentservice.controller;

import com.example.agentservice.dto.CreateUserDTO;
import com.example.agentservice.service.MerchantService;
import com.example.agentservice.service.UserService;
import com.example.agentservice.util.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getpriviledges")
    public ResponseEntity<Response> getPriviledges(@RequestHeader("Authorization")String authorization){
        try {
            return new ResponseEntity<>(userService.getPriviledges(authorization), HttpStatus.OK);
        }catch (Exception e){
            log.info("getpriviledges Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getroles")
    public ResponseEntity<Response> getRoles(@RequestHeader("Authorization")String authorization){
        try {
            return new ResponseEntity<>(userService.getRoles(authorization), HttpStatus.OK);
        }catch (Exception e){
            log.info("getpriviledges Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("createUser")
    public ResponseEntity<Response> createUser(@RequestHeader("Authorization") String authHeader, @RequestBody CreateUserDTO request){
        try {
            return new ResponseEntity<>(userService.createUser(authHeader,request), HttpStatus.OK);
        }catch (Exception e){
            log.info("Error is {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }


}
