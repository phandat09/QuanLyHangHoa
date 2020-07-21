package com.inventory_manegement.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inventory_manegement.entities.Users;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	/**
	 * Hàm này sẽ gọi tới Repository để lấy dữ liệu.
	 */
	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = (Users) userService.getAccount(username);
		String pass = users.getPassword();
		User user = new User(username, pass,  Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
		return user;
	}
}
