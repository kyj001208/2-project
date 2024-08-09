package com.green.petfirst.security;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
	
	ADMIN("관리자"),
	PETFIR("회원");

	private final String rolename;
	
	public final String rolename() {
		
		return rolename;
	}
}
