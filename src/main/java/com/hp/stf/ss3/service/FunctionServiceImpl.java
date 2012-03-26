package com.hp.stf.ss3.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;

import com.hp.stf.module.vo.RoleLinkVo;
import com.hp.stf.ss3.dao.FunctionDao;
import com.hp.stf.ss3.vo.Function;
import com.hp.stf.ss3.vo.Role;

//import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;

public class FunctionServiceImpl implements FunctionService {
	private static final Logger logger = Logger
			.getLogger(FunctionServiceImpl.class);

	private FunctionDao functionDao;

	public void setFunctionDao(FunctionDao resourceDao) {
		this.functionDao = resourceDao;
	}

	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public void init() throws Exception {
		FunctionServiceImpl.resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		// 通过数据库中的信息设置，resouce和role
		for (Function item : functionDao.getAllFunction()) {
			resourceMap.put(item.getUrl(), listToCollection(item.getRoles()));
		}
		System.out.println("#####");
	}

	/**
	 * 自定义方法，将List<Role>集合转换为框架需要的Collection<ConfigAttribute>集合
	 * 
	 * @param roles
	 * @return
	 */
	private Collection<ConfigAttribute> listToCollection(List<Role> roles) {
		List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
		for (Role role : roles) {
			list.add(new SecurityConfig(role.getName()));
		}
		return list;
	}

	/**
	 * 接口中规定的方法，用户获取正在访问的资源所对应的权限结合
	 */
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// FilterInvocation fi = (FilterInvocation) object;
		// UsernamePasswordAuthenticationToken upat = null;
		// org.springframework.security.authentication.UsernamePasswordAuthenticationToken@5bbbd94d:
		// Principal: com.hp.stf.ss3.vo.User@60ec0b80; Credentials: [PROTECTED];
		// Authenticated: true; Details:
		// org.springframework.security.web.authentication.WebAuthenticationDetails@0:
		// RemoteIpAddress: 0:0:0:0:0:0:0:1; SessionId:
		// AC216151E17BB9DCC8914E4BF8AD3E0C; Granted Authorities: ROLE_admin
		// [--][DEBUG]Voter:
		// org.springframework.security.access.vote.RoleVoter@146ccf3e,
		// returned: -1
		// [--][DEBUG]Returning cached instance of singleton bean
		// 'org.springframework.security.core.session.SessionRegistryImpl#0'
		// [--][DEBUG]Access is denied (user is not anonymous); delegating to
		// AccessDeniedHandler
		// org.springframework.security.access.AccessDeniedException: Access is
		// denied
		//
		String url = ((FilterInvocation) object).getRequestUrl();
		if (logger.isDebugEnabled()) {
			logger.debug("getAttributes方法:正在访问的URL为:" + url);
		}
		Iterator<String> it = resourceMap.keySet().iterator();
		while (it.hasNext()) {
			String resURL = it.next();
			// 使用AntUrlPathMatcher来比较，也可以使用其它方式来比较，可以根据需要来修改
			// 一旦匹配上，立即返回
			if (urlMatcher.pathMatchesUrl(resURL, url)) {
				Collection<ConfigAttribute> returnCollection = resourceMap
						.get(resURL);
				return returnCollection;
			}
		}
		// 没有匹配上，则返回null
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	/**
	 * 获取所有权限配置属性
	 */
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
		for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap
				.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}
		return allAttributes;
	}

	@Override
	public List<Function> getFunctionByRoleName(String roleName) {
		return functionDao.getFunctionByRoleName(roleName);
	}

	public List<RoleLinkVo> transFunctionToMenu(List<Function> functionListAll) {
		// doLogin doHeader doLeft doRight doBottom
		List<RoleLinkVo> roleLinkList = new ArrayList<RoleLinkVo>();

		functionListAll = filterFrames(functionListAll);

		orderFunctionList(functionListAll);
		List<String> topFunctionList = getTopFunctionList(functionListAll);
		RoleLinkVo roleLinkVo = null;
		for (Iterator<String> iterator1 = topFunctionList.iterator(); iterator1
				.hasNext();) {
			String string = (String) iterator1.next();

			// TODO Auto-generated method stub
			for (Iterator<Function> iterator2 = functionListAll.iterator(); iterator2
					.hasNext();) {
				Function function = (Function) iterator2.next();
				if (string.equals(function.getName())) {
					roleLinkVo = new RoleLinkVo();
					roleLinkVo.setName(function.getFunction_display());
					roleLinkVo.setFlag("1");
					List<RoleLinkVo> subList = getSubList(string,
							functionListAll);
					roleLinkVo.setList(subList);
				}

			}

			roleLinkList.add(roleLinkVo);
		}

		return roleLinkList;
	}

	private void orderFunctionList(List<Function> functionListAll) {

		// 创建一个比较器匿名类
		Comparator<Function> comparator = new Comparator<Function>() {
			public int compare(Function op1, Function op2) {
				// 按姓名排序
				return op1.getFunction_order().compareTo(
						op2.getFunction_order());
			}
		};

		// 排序
		Collections.sort(functionListAll, comparator);
	}

	private List<RoleLinkVo> getSubList(String string,
			List<Function> functionListAll) {
		List<RoleLinkVo> subList = new ArrayList<RoleLinkVo>();
		RoleLinkVo roleLinkVo = null;
		for (Iterator<Function> iterator2 = functionListAll.iterator(); iterator2
				.hasNext();) {
			Function function = (Function) iterator2.next();
			if (string.equals(function.getFunction_parent())) {
				roleLinkVo = new RoleLinkVo();
				roleLinkVo.setName(function.getFunction_display());
				roleLinkVo.setFlag("0");
				// 去掉末尾的星号*
				roleLinkVo.setLink(function.getUrl().substring(0,
						function.getUrl().length() - 1));
				subList.add(roleLinkVo);
			}
		}

		return subList;
	}

	private List<Function> filterFrames(List<Function> functionListAll) {
		List<Function> returnList = new ArrayList<Function>();
		for (Iterator<Function> iterator = functionListAll.iterator(); iterator
				.hasNext();) {
			Function function = (Function) iterator.next();
			if (!"1".equals(function.getFunction_order())) {
				returnList.add(function);
			}
		}
		return returnList;
	}

	private List<String> getTopFunctionList(List<Function> functionListAll) {
		List<String> returnList = new ArrayList<String>();
		for (Iterator<Function> iterator = functionListAll.iterator(); iterator
				.hasNext();) {
			Function function = (Function) iterator.next();
			// 过滤掉Login对应的order TODO
			if (!"000000".equals(function.getFunction_order())
					&& "0000".equals(function.getFunction_order().substring(2,
							6))) {
				returnList.add(function.getName());
			}
		}
		return returnList;
	}

}
