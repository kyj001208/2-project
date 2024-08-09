package com.green.petfirst.security;


import java.util.Map;
import java.util.stream.Collectors;



import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.green.petfirst.domain.entity.MemberEntity;

import lombok.Getter;

@Getter
public class PetfirUserDetails extends User{
	
	
private static final long serialVersionUID = 1L;
	
	//principal 에서 확인하기 위해 추가로 등록 가능(선택 사항)
	private String userid; //username과 동일 
	private String name;
	private String email;
	
	private  Map<String, Object> attributes;
	

	public PetfirUserDetails(MemberEntity entity){
		super(entity.getUserId(),entity.getPassword(),
				entity.getRoles().stream()
				.map(role->new SimpleGrantedAuthority("ROLE_"+role))
				.collect(Collectors.toSet()));
		
		
		userid=entity.getUserId();
		name=entity.getName();
		email=entity.getEmail();
	
	}
	
	

}
