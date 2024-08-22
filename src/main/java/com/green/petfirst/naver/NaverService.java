package com.green.petfirst.naver;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.petfirst.domain.entity.JogicdoEntity;
import com.green.petfirst.domain.repository.JogicdoRepository;
import com.green.petfirst.naver.dto.NaverDTO;
import com.green.petfirst.naver.dto.ResponseResultDTO;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class NaverService {
	
	private final OpenApiUtil openApiUtil;
	private final JogicdoRepository repository;
	

	@Value("${naver.client.id}")
	private String clientId;
	
	@Value("${naver.client.secret}")
	private String clientSecret;
	
	@Value("${naver.client.domain}")
	private String domainId;
	
	
	
	public void orgUnitRead(String code, Model model) throws Exception {
		
		String responResult=getAccessToken(code);
		NaverDTO result=openApiUtil.objectMapper(responResult, new TypeReference<NaverDTO>(){}); 
		
		String accessToken=result.getAccess_token();
		
		
		String apiUrl="https://www.worksapis.com/v1.0/orgunits";
		
		StringBuilder urlBuilder= new StringBuilder(apiUrl);
		urlBuilder.append("?domainId=");urlBuilder.append(domainId);
		apiUrl=urlBuilder.toString();
		String method="GET";
		
		Map<String, String> headers=new HashMap<>();
		headers.put("Authorization", "Bearer "+accessToken);
	
		String orgUnitResponseresult=openApiUtil.request(apiUrl, headers, method, null);
		System.out.println("orgUnitResponseresult" + orgUnitResponseresult);
		
		ResponseResultDTO resultOrgUnits=openApiUtil.objectMapper(orgUnitResponseresult, new TypeReference<ResponseResultDTO>() {});
		model.addAttribute("list",resultOrgUnits.getOrgUnits());
		
	}


	private String getAccessToken(String code) {
		
		String apiUrl="https://auth.worksmobile.com/oauth2/v2.0/token";
		
	
		StringBuilder urlBuilder= new StringBuilder(apiUrl);
		urlBuilder.append("?code=");urlBuilder.append(code);
		urlBuilder.append("&grant_type=authorization_code");
		urlBuilder.append("&client_id=");urlBuilder.append(clientId);
		urlBuilder.append("&client_secret=");urlBuilder.append(clientSecret);
		apiUrl=urlBuilder.toString();
		
		
		String method="POST";
		
		Map<String, String> headers=new HashMap<>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		
		
	
		return openApiUtil.request(apiUrl, headers, method, null);
		
	}


	public void listProcess(Model model) {
	
		model.addAttribute("mem",repository.findAll().stream()
		 .map(JogicdoEntity::toListDTO).collect(Collectors.toList()));
	}







}
