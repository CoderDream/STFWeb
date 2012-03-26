package com.hp.stf.ss3.dao;


import java.util.List;

import com.hp.stf.ss3.vo.Function;

public interface FunctionDao {
	public List<Function> getAllFunction();
	public List<Function> getFunctionByRoleName(String roleName);
}
