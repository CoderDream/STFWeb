package com.hp.stf.ss3.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 387544223460755224L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String name;
	private String password;
	private int state;
	private String desc;
	private List<Role> roles;

	// 省略getter setter方法
	// 获取用户名
	public String getPassword() {
		return password;
	}

	// 获取密码
	public String getUsername() {
		return this.name;
	}

	// 直接返回true，表示没有过期
	public boolean isAccountNonExpired() {
		return true;
	}

	// 直接返回true，表示没有锁定
	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 是否禁用
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.state == 0 ? false : true;
	}

	// 获取用户权限集合,权限使用GrantedAuthority接口表示，框架中有它的实现类//GrantedAuthorityImpl,只需要把角色的名称放入即可
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		for (Role role : roles) {
			list.add(new GrantedAuthorityImpl(role.getName()));
		}
		return list;
	}
}
