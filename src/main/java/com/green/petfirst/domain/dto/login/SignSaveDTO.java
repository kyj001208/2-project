package com.green.petfirst.domain.dto.login;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.petfirst.domain.entity.login.SignupEntity;

import lombok.Setter;

@Setter
public class SignSaveDTO {
	
	private String userid; 
	
	private String password; 
	
	private String username; 
	
	private String adress; 
	
	private String usernumber; 
	
	private String useremail; 
	
	private String petname; 
	
	private String petbreed;

	public SignupEntity toEntity(PasswordEncoder pass) {
		
		return SignupEntity.builder()
				.userID(userid)
				.password(pass.encode(password))
				.name(username)
				.adress(adress)
				.usernumber(usernumber)
				.useremail(useremail)
				.petname(petname)
				.petbreed(petbreed)
				.build();
	}



	

}
