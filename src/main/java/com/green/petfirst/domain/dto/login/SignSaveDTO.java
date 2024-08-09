package com.green.petfirst.domain.dto.login;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.petfirst.domain.entity.MemberEntity;

import lombok.Setter;

@Setter
public class SignSaveDTO {
	
	private String userId; 
	
	private String password; 
	
	private String name; 
	
	private String adress; 
	
	private String phone; 
	
	private String email; 
	
	private String petName; 
	
	private String petBreed;

	public MemberEntity toEntity(PasswordEncoder pass) {
		
		return MemberEntity.builder()
				.userId(userId)
				.password(pass.encode(password))
				.name(name)
				.adress(adress)
				.phone(phone)
				.email(email)
				.petName(petName)
				.petBreed(petBreed)
				.build();
	}



	

}
