package com.hp.stf.ss3.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Function implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String url;
	private List<Role> roles = new ArrayList<Role>();
	
	private String function_desc;
	private String function_display;
	private String function_parent;
	private String function_order;
	private boolean function_enable;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getFunction_desc() {
		return function_desc;
	}

	public void setFunction_desc(String function_desc) {
		this.function_desc = function_desc;
	}

	public String getFunction_display() {
		return function_display;
	}

	public void setFunction_display(String function_display) {
		this.function_display = function_display;
	}

	public String getFunction_parent() {
		return function_parent;
	}

	public void setFunction_parent(String function_parent) {
		this.function_parent = function_parent;
	}

	public String getFunction_order() {
		return function_order;
	}

	public void setFunction_order(String function_order) {
		this.function_order = function_order;
	}

	public boolean isFunction_enable() {
		return function_enable;
	}

	public void setFunction_enable(boolean function_enable) {
		this.function_enable = function_enable;
	}

	
}
