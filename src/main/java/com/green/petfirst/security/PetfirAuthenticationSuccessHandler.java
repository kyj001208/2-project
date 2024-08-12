package com.green.petfirst.security;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PetfirAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	//RequestCache : 사용자가 인증되지 않았던 페이지에 대한 정보를 저장하는 객체 //인증성공 후 저장된 요청을 복원하여 해당 페이지로 리다렉트
		private final RequestCache requestCache=new HttpSessionRequestCache();
		
		private final RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
		
	
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
		

			clearAuthenticationAttributes(request); //인증실패하거나 인증관련 메시지와 상태정보가 이후 요청에 영향을 미치지 않도록 제거
			
			String targetUrl="/";  //기본 리다이렉트 url
			
			// SavedRequest를 통해 원래 요청했던 URL 가져오기
			SavedRequest savedRequest=requestCache.getRequest(request, response);
			
			 if (savedRequest != null) {
		            // 저장된 요청의 URL을 가져와 리다이렉트 URL로 설정
		            targetUrl = savedRequest.getRedirectUrl().split("[?]")[0];
		            
		        } else {
		            // 원래 요청된 URL이 없을 경우 세션에서 이전 페이지 URL 가져오기
		            String prevPage = (String) request.getSession().getAttribute("prevPage");
		            
		            if (prevPage != null) {
		                targetUrl = prevPage;
		                request.getSession().removeAttribute("prevPage");
		            }
		        }
		        
		        // 권한에 따라 URL 설정 어드민 권한 리다이렉트 
		        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		        boolean hasAdminRole = authorities.stream()
		                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN")); //어드민
		       
		      
		        
		        if (hasAdminRole) {
		            targetUrl = "/admin/petfir";
		        } 
		        // 리다이렉트 수행
		        redirectStrategy.sendRedirect(request, response, targetUrl);
		    }


}
