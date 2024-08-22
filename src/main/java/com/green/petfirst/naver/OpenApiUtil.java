package com.green.petfirst.naver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class OpenApiUtil {
	
	/**
	 * 
	 * @param apiUrl 요청url
	 * @param requestHeaders 요청시 header 들어가는 정보가 있으면 세팅 필요없으면 null 입력
	 * @methodType 메서드방식  POST or GET
	 * @requestBody 없으면 null 있으면 JSON형식 문자열
	 * @return JSON문자열 형식으로 리턴됨
	 */
	public String request(String apiUrl, Map<String, String> requestHeaders, String methodType, String requestBody){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod(methodType);
            
            if(requestHeaders!=null) {
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }
            }
            
            //POST 일때 필요시 requestBody 입력을 JSON
            if(requestBody!=null) {
            	con.setDoOutput(true);
            	try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                    wr.write(requestBody.getBytes());
                    wr.flush();
                }
            }
            
            
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
            	//json 데이터 읽기처리
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }//request
	
	private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }//connect
	
	//is->
	private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);
        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();
            
            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
	
	/**
	 * 
	 * @param <T>
	 * @param responseJSONData : 객체로 변환할 JSON형식의 문자열 data
	 * @param typeReference : 변환할 객체를 T(제네릭)타입에 정의
	 * @return
	 * @throws Exception
	 */
	public <T> T objectMapper(String responseJSONData, TypeReference<T> typeReference) throws Exception {
		return  new ObjectMapper().readValue(responseJSONData, typeReference);
	}
	

}
