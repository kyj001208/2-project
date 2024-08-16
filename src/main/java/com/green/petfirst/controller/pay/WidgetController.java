package com.green.petfirst.controller.pay;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class WidgetController {

    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper

    @PostMapping("/sandbox-dev/api/v1/payments/confirm")
    public ResponseEntity<JsonNode> confirmPayment(@RequestBody String jsonBody) throws Exception {

        // JSON 요청 바디를 파싱합니다.
        JsonNode requestData = objectMapper.readTree(jsonBody);
        String paymentKey = requestData.path("paymentKey").asText();
        String orderId = requestData.path("orderId").asText();
        String amount = requestData.path("amount").asText();

        // 요청 본문을 구성합니다.
        JsonNode requestPayload = objectMapper.createObjectNode()
                .put("orderId", orderId)
                .put("amount", amount)
                .put("paymentKey", paymentKey);

        // 시크릿 키를 Base64로 인코딩합니다.
        String widgetSecretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";
        String encodedCredentials = Base64.getEncoder()
                .encodeToString((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        String authorizationHeader = "Basic " + encodedCredentials;

        // 결제 API에 요청을 보냅니다.
        URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizationHeader);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(requestPayload.toString().getBytes(StandardCharsets.UTF_8));
        }

        int responseCode = connection.getResponseCode();
        InputStream responseStream = responseCode == 200 ? connection.getInputStream() : connection.getErrorStream();

        JsonNode responseJson = objectMapper.readTree(responseStream);

        return ResponseEntity.status(responseCode).body(responseJson);
    }
}
