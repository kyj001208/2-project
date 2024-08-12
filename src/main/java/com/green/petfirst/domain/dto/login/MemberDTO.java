package com.green.petfirst.domain.dto.login;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.petfirst.domain.entity.MemberEntity;
import com.green.petfirst.security.Role;

import lombok.Setter;

@Setter
public class MemberDTO {
	
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
				.roles(Set.of(Role.PETFIR))
				.build();
	}



	

}
