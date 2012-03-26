package com.hp.stf.ss3.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hp.stf.ss3.dao.UserDao;
import com.hp.stf.ss3.vo.User;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		System.out.println("username=================" + username);
		User user = userDao.findUserByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户名" + username + "不存在");
		}
		// 因为User已经实现了UserDetails接口，所以直接返回user即可
		return user;
	}

	@Override
	public User findUser(String user_name, String password) {
		return userDao.findUser(user_name, password);
	}

	@Override
	public int updateUserPassword(String user_name, String password) {
		return userDao.updateUserPassword(user_name, password);
	}
}
