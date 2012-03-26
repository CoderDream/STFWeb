package com.hp.stf.ss3.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.hp.stf.module.utils.Md5Util;
import com.hp.stf.ss3.vo.Role;
import com.hp.stf.ss3.vo.User;

public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
	private Logger logger = Logger.getLogger(UserDaoImpl.class);

	private List<Role> getRolesByUserID(String username) {
		String sql = "SELECT r.role_name,r.role_desc FROM role r,user_role ur WHERE r.role_name=ur.role_name AND ur.user_name=?";
		return getJdbcTemplate().query(sql, new String[] { username },
				new RowMapper<Role>() {
					public Role mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Role role = new Role();
						role.setName(rs.getString(1));
						role.setDesc(rs.getString(2));
						return role;
					}
				});
	}

	public User findUserByName(final String name) {
		String sql = "select user_name,user_password,user_enable FROM user where user_name=?";
		User user = null;
		try {
			user = this.getJdbcTemplate().queryForObject(sql,
					new Object[] { name }, new RowMapper<User>() {
						public User mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							User user = new User();
							user.setName(rs.getString("user_name"));
							user.setPassword(rs.getString("user_password"));
							user.setState(rs.getInt("user_enable"));
							// 获取用户所有的权限
							user.setRoles(getRolesByUserID(name));
							return user;
						}
					});
		} catch (Exception ex) {
			if (logger.isInfoEnabled()) {
				logger.info("用户名不存在!");
			}
		}
		return user;
	}

	@Override
	public User findUser(final String user_name, String password) {
		String sql = "select user_name,user_password,user_enable FROM user where user_name=? and user_password = ?";
		User user = null;
		String md5Password = Md5Util.getMD5Str(password + "{" + user_name + "}");
		logger.info(user_name + ":" + md5Password);
		try {
			user = this.getJdbcTemplate().queryForObject(sql,
					new Object[] { user_name, md5Password },
					new RowMapper<User>() {
						public User mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							User user = new User();
							user.setName(rs.getString("user_name"));
							user.setPassword(rs.getString("user_password"));
							user.setState(rs.getInt("user_enable"));
							// 获取用户所有的权限
							user.setRoles(getRolesByUserID(user_name));
							return user;
						}
					});
		} catch (Exception ex) {
			if (logger.isInfoEnabled()) {
				logger.info("用户名不存在!");
			}
		}

		System.out.println("#################");
		return user;
	}

	@Override
	public int updateUserPassword(String user_name, String password) {

		String md5Password = Md5Util.getMD5Str(password + "{" + user_name + "}");
		logger.info("#### Update: " + user_name + ":" + md5Password);
		String sql = "update user set user_password ='" + md5Password
				+ "' where user_name='" + user_name + "'";
		int result = -1;

		try {
			result = this.getJdbcTemplate().update(sql);
		} catch (Exception ex) {
			if (logger.isInfoEnabled()) {
				logger.info("用户名不存在!");
			}
		}

		return result;
	}
}
