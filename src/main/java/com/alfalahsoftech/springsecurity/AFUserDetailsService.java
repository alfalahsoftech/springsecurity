package com.alfalahsoftech.springsecurity;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AFUserDetailsService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usr = null;
		// load user from DB
		System.out.println("Loading user details..."+ username);
//		User usr = new User("admin", "admin",new ArrayList<GrantedAuthority>());
		HashMap<String,User> usersMap = new HashMap<>();
		usersMap.put("admin",new User("admin", "admin",getUsersRole(username)));
		usersMap.put("dev",new User("dev", "dev",getUsersRole(username)));
		System.out.println("Leve 1");
		usr = usersMap.get(username);
		if(usr == null) {
			System.out.println("User not found!!!");
			throw  new UsernameNotFoundException("User Not Found");
		}
		System.out.println("Username: " + usr.getUsername());
		System.out.println("Password: " + usr.getPassword());
		System.out.println("Password: " + usr.getAuthorities());
		return usr;
	}
	
	
	
	public ArrayList<GrantedAuthority> getUsersRole(String user){
		HashMap<String,ArrayList<GrantedAuthority>> usersRoleMap = new HashMap<>();
		usersRoleMap.put("admin",
					new ArrayList<GrantedAuthority>(Arrays.asList("ADMIN")
					.stream()
					.map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
					);
		
		usersRoleMap.put("dev",
				new ArrayList<GrantedAuthority>(Arrays.asList("USER")
				.stream()
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
				);
		return usersRoleMap.get(user);
	}
}
