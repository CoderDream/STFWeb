package com.hp.stf.module.vo;

import java.util.ArrayList;
import java.util.List;

public class RoleLinkVo extends BaseVo {
	
	private static final long serialVersionUID = 1L;

	private String name;//栏目名称
	
	private String link;//链接地址
	
	private String flag;//是否为一级栏目 1为一级栏目， 2为二级栏目
	
	private List<RoleLinkVo> list = new ArrayList<RoleLinkVo>();// 二级栏目
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<RoleLinkVo> getList() {
		return list;
	}

	public void setList(List<RoleLinkVo> list) {
		this.list = list;
	}
	
	
	
}
