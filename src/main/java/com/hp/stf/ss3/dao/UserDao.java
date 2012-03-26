package com.hp.stf.ss3.dao;

import com.hp.stf.ss3.vo.User;

public interface UserDao {

	public User findUserByName(String name);
	
	public User findUser(final String user_name, String password);
	
	public int updateUserPassword(final String user_name, String password);
}
