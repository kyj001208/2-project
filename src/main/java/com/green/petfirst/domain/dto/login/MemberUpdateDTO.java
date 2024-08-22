package com.green.petfirst.domain.dto.login;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class MemberUpdateDTO {
	
	private String address; 
	private String phone; 
	private String petName; 
	private String petBreed;

}
