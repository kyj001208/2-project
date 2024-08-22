package com.green.petfirst.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;

//springsecurity6 & boot 3.0 ~

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final PetfirUserDetailsService petfirUserDetailsService;
	private final PetfirAuthenticationSuccessHandler petfirAuthenticationSuccessHandler;
	private final PetfirOQuth2UserService petfirOQuth2UserService;
	
	//순서가 있기에 아래 순서에 맞게 진행해야함 
	 @Bean
	     SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 

		 	//토스 페이 경우 extraHeaders 불가 지원 안함 그래서 비활성화
		 
	        http
	        	
	        
	            .csrf(Customizer.withDefaults()) //표기하지 않아도 기본으로 crsf 보안이 적용-get 요청을 제외한 모든 요청에 대해 post, put, delete 등 발행 필요
	            //authorizeHttpRequests: 요청하는 url에 대한 보안
	            .authorizeHttpRequests(authorize -> authorize
	            		.requestMatchers("/css/**","/js/**","/img/**").permitAll()
	            		.requestMatchers("/","/public/**","/sandbox-dev/api/v1/payments/confirm").permitAll()
	            		.requestMatchers("/petfir/**").hasAnyRole("PETFIR", "SOCIALUSER","ADMIN")
						.requestMatchers("/chatbot/**").permitAll() // 웹소켓 엔드포인트 허용
						.requestMatchers("/event/**").permitAll()
	            		.requestMatchers("/admin/**").hasRole("ADMIN")

	                    .anyRequest().authenticated() 
	                
	            )
	           
	            .formLogin(login->login
	            		.loginPage("/public/login")
	            		.loginProcessingUrl("/public/login-action")
	            		.permitAll() //해당 메서드를 작성시에는 해당 이름에 맞게 html form태그 이름 맞게 진행
	            		
	            		
	            		.usernameParameter("userId") //default:username //html name과 일치해야함
	            		.passwordParameter("password")  //Defaultis "password" //html name과 일치해야함
	            		.successHandler(petfirAuthenticationSuccessHandler) //로그인 성공 이후에 처리할 내용을 정의하는 클래스 생성 
	            		
	            )
	            .logout(logout->logout
	            		.logoutSuccessUrl("/")//default //로그 아웃 시 인덱스 페이지로 넘어가게끔 설정
	            		.invalidateHttpSession(true)//로그아웃 시 세션에 저장된 데이터를 제거하고, 해당 세션을 더 이상 유효하지 않도록 만듭니다. 
	            )
	            
	            .userDetailsService(petfirUserDetailsService)
	            ;
	        
		 
		 
		 	//설정 후 아래 코드 쓰면 구글 로그인이 뜸 
		 	http
			
			.oauth2Login(ouath2 ->ouath2
			.loginPage("/public/login")
			.userInfoEndpoint(userInfo->userInfo
			.userService(petfirOQuth2UserService)
			)
					
			);  	
	   	 		
	        
	        
		 
	        return http.build();
	    }
	 
			

	

}
