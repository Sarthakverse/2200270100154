package com.eazybytes.loggingmiddleware.service;

import com.eazybytes.loggingmiddleware.constants.AppConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoggingService {
    private final RestTemplate restTemplate = new RestTemplate() ;

    public void log(String stack, String level, String pkg, String message){
        try{
            stack = stack.toLowerCase();
            level = level.toLowerCase();
            pkg = pkg.toLowerCase();

            Map<String, String> body = new HashMap<>();
            body.put("stack", stack);
            body.put("level", level);
            body.put("package", pkg);
            body.put("message", message);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(AppConstants.LOGGING_API_URL, request, String.class);


        } catch (Exception e) {
            // i have not handled the exception here, because no custom logging right
    }
}
}
