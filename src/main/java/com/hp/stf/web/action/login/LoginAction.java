package com.hp.stf.web.action.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.hp.stf.module.service.DemoService;
import com.hp.stf.module.vo.DemoVo;
import com.hp.stf.module.vo.RoleLinkVo;
import com.hp.stf.ss3.service.FunctionService;
import com.hp.stf.ss3.vo.Function;
import com.hp.stf.ss3.vo.User;
import com.hp.stf.web.action.BaseAction;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginAction.class);

	private User user;

	private List<RoleLinkVo> list = new ArrayList<RoleLinkVo>();

	private String contextPath;

	@Resource
	private DemoService demoService;

	@Resource
	private FunctionService functionService;

	public String login() {
		logger.debug("enter login() method");

		return SUCCESS;
	}

	public String doLogin() {
		DemoVo demoVo = new DemoVo();
		demoVo.setPassword("123456");
		demoVo.setUser("Jacky");
		demoVo.setUsername("zhujian");

		contextPath = ServletActionContext.getServletContext().getContextPath();

		try {
			demoService.log(demoVo);
		} catch (Exception e) {
			logger.error("-------------> catched exception [" + e.getMessage()
					+ "]");
			logger.error(e.getMessage(), e);
		}
		
		addActionMessage("Password Reset Success!");
		
		return SUCCESS;
	}

	public String doLogout() {
		logger.debug("enter doLogout() method");
		return SUCCESS;
	}

	public String doHeader() {
		logger.debug("enter doHeader() method");
		return SUCCESS;
	}

	public String doUserInfo() {
		logger.debug("enter doUserInfo() method");
		return SUCCESS;
	}

	public String doRight() {
		logger.debug("enter doRight() method");
		return SUCCESS;
	}

	public String doBottom() {
		logger.debug("enter doBottom() method");
		return SUCCESS;
	}

	public String doLeft() {
		List<Function> functionListAll = new ArrayList<Function>();
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		Collection<GrantedAuthority> authorities = userDetails.getAuthorities();
		for (GrantedAuthority au : authorities) {
			logger.debug("au.getAuthority()========================================="
					+ au.getAuthority());

			String roleName = au.getAuthority();

			// Role[] role = userDetails.getAuthorities().getClass();
			List<Function> functionList = functionService
					.getFunctionByRoleName(roleName);

			for (Function function : functionList) {
				System.out.println(function.getFunction_display()
						+ function.getUrl() + function.getFunction_order());
			}
			functionListAll.addAll(functionList);
		}
		list = functionService.transFunctionToMenu(functionListAll);

		// List<RoleLinkVo> templist = transFunctionToMenu(functionListAll);
		// List<RoleLinkVo> templist =
		// functionService.transFunctionToMenu(functionListAll);

		// List<RoleLinkVo> subList = new ArrayList<RoleLinkVo>();
		// RoleLinkVo linkVo = new RoleLinkVo();
		// linkVo = new RoleLinkVo();
		// linkVo.setName("Reset Password");
		// linkVo.setFlag("0");
		// linkVo.setLink("/resetpassword/resetpassword.action");
		// subList.add(linkVo);
		//
		// linkVo = new RoleLinkVo();
		// linkVo.setName("Security Question");
		// linkVo.setFlag("0");
		// linkVo.setLink("/mail/list.action");
		// subList.add(linkVo);
		//
		// linkVo = new RoleLinkVo();
		// linkVo.setName("Basic Setup");
		// linkVo.setFlag("1");
		// linkVo.setList(subList);
		// list.add(linkVo);
		//
		// linkVo = new RoleLinkVo();
		// linkVo.setName("Admin");
		// linkVo.setFlag("1");
		// list.add(linkVo);
		//
		// ServletActionContext.getRequest().setAttribute("permission",
		// templist);
		return SUCCESS;
	}

	public List<RoleLinkVo> getList() {
		return list;
	}

	public void setList(List<RoleLinkVo> list) {
		this.list = list;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public DemoService getDemoService() {
		return demoService;
	}

	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}

	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
