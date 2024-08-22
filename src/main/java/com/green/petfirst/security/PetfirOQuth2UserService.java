package com.green.petfirst.security;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.green.petfirst.domain.entity.MemberEntity;
import com.green.petfirst.service.login.MemberService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class PetfirOQuth2UserService  extends DefaultOAuth2UserService{
	
	private final PasswordEncoder pass;
	private final MemberService service;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oAuth2User=super.loadUser(userRequest);
		
		//db에 저장하기 위한 정보 조회
		String registrationId=userRequest.getClientRegistration().getRegistrationId();
		System.out.println(registrationId);
		//인증정보를 제공하는 폼이 다르다 그러기위해 아이디로 구별
		
		Map<String, Object> attributes=oAuth2User.getAttributes(); //구글기준임 (네이버랑 카카오는 다를수 있어요)
		
		for(String attributeName:attributes.keySet()) {
			System.out.print(attributeName+":");	
			System.out.println(attributes.get(attributeName));	
		}
		
		
		return socialUser(oAuth2User,registrationId ); //커스텀 유저 디테일즈에 포함됌
	}
	
	private OAuth2User socialUser(OAuth2User oAuth2User, String registrationId) {

		String email = null;
		String name = null;

		if (registrationId.equals("google")) {

			email = oAuth2User.getAttribute("email");
			name = oAuth2User.getAttribute("name");

		}else if (registrationId.equals("naver")) {

			Map<String, Object> response = oAuth2User.getAttribute("response");

			email = (String) response.get("email");

			name = (String) response.get("name");

		} else if (registrationId.equals("kakao")) {

			Map<String, Object> response = oAuth2User.getAttribute("kakao_account");

			Map<String, Object> profile = (Map<String, Object>) response.get("profile");

			email = (String) response.get("email");

			name = (String) response.get("name");

		}
	

		MemberEntity entity = MemberEntity.builder()

				.email(email).name(name)

				.password(pass.encode(String.valueOf(System.currentTimeMillis())))

				.build().addRole(Role.SOCIALUSER);
		
		service.saveSocial(entity);

		return new PetfirUserDetails(entity);

		}



}
