package com.soloscholar.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
	public class RoleController {

	    @GetMapping("/dashboard")
	    public String dashboard(Authentication authentication) {
	        if (authentication != null && authentication.getAuthorities().stream()
	                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("admin"))) {
	            return "redirect:/admin-login";
	        } else if (authentication != null && authentication.getAuthorities().stream()
	                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("staff"))) {
	            return "redirect:/user-login";
	        } else if (authentication != null && authentication.getAuthorities().stream()
	                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("reader"))) {
	            return "redirect:/reader-login";
	        }
	        return "redirect:/access-denied";
	    }
	}


