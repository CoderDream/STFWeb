package com.hp.stf.ss3.service;

import java.util.List;

import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.hp.stf.module.vo.RoleLinkVo;
import com.hp.stf.ss3.vo.Function;

public interface FunctionService extends FilterInvocationSecurityMetadataSource {
	public List<Function> getFunctionByRoleName(String roleName);
	
	public List<RoleLinkVo> transFunctionToMenu(List<Function> functionListAll);
}
