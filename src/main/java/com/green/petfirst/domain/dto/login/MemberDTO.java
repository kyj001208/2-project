package com.green.petfirst.domain.dto.login;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.petfirst.domain.entity.MemberEntity;
import com.green.petfirst.security.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Setter
public class MemberDTO {
	
	private long id;
	
	private String userId; 
	
	private String password; 
	
	private String name; 
	
	private String address; 
	
	private String phone; 
	
	private String email; 
	
	private String petName; 
	
	private String petBreed;

	public MemberEntity toEntity(PasswordEncoder pass) {
		
		return MemberEntity.builder()
				.memNo(this.id)
				.userId(userId)
				.password(pass.encode(password))
				.name(name)
				.address(address)
				.phone(phone)
				.email(email)
				.petName(petName)
				.petBreed(petBreed)
				.roles(Set.of(Role.PETFIR))
				.build();
	}

}
