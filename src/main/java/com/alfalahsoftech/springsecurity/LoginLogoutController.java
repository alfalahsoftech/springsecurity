package com.alfalahsoftech.springsecurity;

import java.util.Collections;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginLogoutController {
 
	@GetMapping("/loginUser")
	public java.util.Map<String,String> login() {
		System.out.println("Login user.....");
		return Collections.singletonMap("message", "Successfully login");
	}
	
	@GetMapping("/home")
	public java.util.Map<String,String> homePage() {
		System.out.println("Loading homepage....");
		return Collections.singletonMap("message", "Welcome to home page :)");
	}
	

	@GetMapping("/admin")
	public java.util.Map<String,String> adminHomePage() {
		System.out.println("inside adminHomePage....");
		return Collections.singletonMap("message", "Welcome to ADMIN home page :)");
	}
}
