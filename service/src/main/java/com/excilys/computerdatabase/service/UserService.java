package com.excilys.computerdatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.model.User;
import com.excilys.computerdatabase.persistence.UserDAO;

@Service
public class UserService  implements UserDetailsService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public User loadUserByUsername(String name) throws UsernameNotFoundException {
		return userDAO.get(name).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
