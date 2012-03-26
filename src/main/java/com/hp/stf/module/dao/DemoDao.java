package com.hp.stf.module.dao;

import com.hp.stf.module.entity.Demo;

public class DemoDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return Demo.class;
	}

}
