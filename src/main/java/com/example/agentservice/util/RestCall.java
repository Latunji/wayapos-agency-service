package com.example.agentservice.util;

import com.example.agentservice.dto.AddRequirementDto;
import com.example.agentservice.dto.BankAccountDto;
import com.example.agentservice.dto.CreateKycDto;
import com.example.agentservice.dto.KycResponseDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class RestCall {

    public String executeRequest(String token, CreateKycDto createKycDto, String createKycUrl) throws JSONException, IOException {
        HttpURLConnection connection = null;
        JSONObject js = new JSONObject();
        js.put("customerId", createKycDto.getCustomerId());
        js.put("customerName", createKycDto.getCustomerName());
        js.put("customerPhoneNumber", createKycDto.getCustomerPhoneNumber());
        js.put("customerEmail", createKycDto.getCustomerEmail());
        try {
            URL url = new URL(createKycUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", token);
            connection.setDoOutput(true);

            String dataToSend = js.toString();
            System.out.println("Data To Send..."+dataToSend);
            try ( OutputStream wr = connection.getOutputStream()) {
                byte[] in = dataToSend.getBytes(StandardCharsets.UTF_8);
                wr.write(in, 0, in.length);
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
            return myResponse.toString();
        } catch (IOException e) {
            log.info("error... "+e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    public String addBanks(String token, BankAccountDto bankAccountDto, String addBankUrl) throws JSONException, IOException {
        HttpURLConnection connection = null;
        JSONObject js = new JSONObject();
        js.put("accountName", bankAccountDto.getAccountName());
        js.put("accountNumber", bankAccountDto.getAccountNumber());
        js.put("bankName", bankAccountDto.getBankName());
        js.put("bankCode", bankAccountDto.getBankCode());
        try {
            URL url = new URL(addBankUrl+"/"+bankAccountDto.getUserId());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", token);
            connection.setDoOutput(true);

            String dataToSend = js.toString();
            System.out.println("Data To Send..."+dataToSend);
            try ( OutputStream wr = connection.getOutputStream()) {
                byte[] in = dataToSend.getBytes(StandardCharsets.UTF_8);
                wr.write(in, 0, in.length);
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
            return myResponse.toString();
        } catch (IOException e) {
            log.info("error... "+e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    public String executeRequestAddKyc(String token, AddRequirementDto addReqDto, String addKycUrl) throws JSONException, IOException {
        HttpURLConnection connection = null;
        JSONObject js = new JSONObject();
        js.put("customerId", addReqDto.getCustomerId());
        js.put("reqValue", addReqDto.getReqValue());
        js.put("reqItem", addReqDto.getReqItem());
        js.put("status", "APPROVE");
        js.put("tierName", "tier1");
        try {
            URL url = new URL(addKycUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", token);
            connection.setDoOutput(true);

            String dataToSend = js.toString();
            System.out.println("Data To Send..."+dataToSend);
            try ( OutputStream wr = connection.getOutputStream()) {
                byte[] in = dataToSend.getBytes(StandardCharsets.UTF_8);
                wr.write(in, 0, in.length);
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
            return myResponse.toString();
        } catch (IOException e) {
            log.info("error... "+e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
