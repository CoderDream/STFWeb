package com.hp.stf.ss3.service;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.hp.stf.ss3.vo.User;

public interface UserService extends UserDetailsService{
	public User findUser(final String user_name, String password);
	public int updateUserPassword(String user_name, String password);
}
