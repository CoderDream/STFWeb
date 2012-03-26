package com.hp.stf.ss3.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.hp.stf.ss3.vo.Function;
import com.hp.stf.ss3.vo.Role;

public class FunctionDaoImpl extends JdbcDaoSupport implements FunctionDao {
	public List<Function> getAllFunction() {
		List<Function> list = null;
		String sql = "SELECT function_name, function_path from function";
		list = this.getJdbcTemplate().query(sql, new RowMapper<Function>() {
			public Function mapRow(ResultSet rs, int arg1) throws SQLException {
				Function resource = new Function();
				resource.setName((rs.getString("function_name")));
				resource.setRoles(getRoleByFunctionName(resource.getName()));
				resource.setUrl(rs.getString("function_path"));
				return resource;
			}
		});
		return list;
	}

	@Override
	public List<Function> getFunctionByRoleName(String roleName) {
		List<Function> list = null;
		StringBuffer sql = new StringBuffer("SELECT ");
		sql.append("function.function_name function_name, ");
		sql.append("function.function_desc function_desc, ");
		sql.append("function.function_display function_display, ");
		sql.append("function.function_path function_path, ");
		sql.append("function.function_parent function_parent, ");
		sql.append("function.function_order function_order, ");
		sql.append("function.function_enable function_enable  ");
		sql.append("from function, acl ");
		sql.append("WHERE acl.function_name = function.function_name  ");
		sql.append("AND acl.role_name=? ");
		sql.append("ORDER BY function.function_order ");
		list = this.getJdbcTemplate().query(sql.toString(), new Object[] { roleName },
				new RowMapper<Function>() {
					public Function mapRow(ResultSet rs, int arg1)
							throws SQLException {
						Function function = new Function();
						function.setName((rs.getString("function_name")));
						function.setRoles(getRoleByFunctionName(function
								.getName()));
						function.setUrl(rs.getString("function_path"));
						
						function.setFunction_desc(rs.getString("function_desc"));
						function.setFunction_display(rs.getString("function_display"));
						function.setFunction_parent(rs.getString("function_parent"));
						function.setFunction_order(rs.getString("function_order"));
						function.setFunction_enable(rs.getBoolean("function_enable"));
						
						return function;
					}
				});
		return list;
	}

	private List<Role> getRoleByFunctionName(String functionName) {
		String sql = "SELECT acl.role_name FROM acl acl WHERE acl.function_name=?";
		return this.getJdbcTemplate().query(sql, new Object[] { functionName },
				new RowMapper<Role>() {
					public Role mapRow(ResultSet rs, int arg1)
							throws SQLException {
						Role role = new Role();
						role.setName(rs.getString(1));
						return role;
					}
				});
	}

}
