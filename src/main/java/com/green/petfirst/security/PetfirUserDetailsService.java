package com.green.petfirst.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


import com.green.petfirst.domain.repository.SignupRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PetfirUserDetailsService implements UserDetailsService{
	
	private final SignupRepository repository;

	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
	
	// TODO Auto-generated method stub
	
		
		return new PetfirUserDetails(repository.findByUserId(userid).orElseThrow());

	}

	


}
