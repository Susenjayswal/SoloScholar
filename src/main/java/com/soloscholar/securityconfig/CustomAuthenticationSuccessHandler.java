package com.soloscholar.securityconfig;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	                                    Authentication authentication) throws IOException, ServletException {
	    System.out.println("Authentication Successful!");
	    System.out.println("Authorities: " + authentication.getAuthorities());

	    if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("admin"))) {
	        System.out.println("Redirecting to /admin-login");
	        response.sendRedirect("/admin-login");
	    } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("staff"))) {
	        System.out.println("Redirecting to /user-login");
	        response.sendRedirect("/user-login");
	    } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("reader"))) {
	        System.out.println("Redirecting to /reader-login");
	        response.sendRedirect("/reader-login");
	    } else {
	        response.sendRedirect("/");
	    }
	}
}
