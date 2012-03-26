package com.hp.stf.module.service;

import javax.annotation.Resource;

import com.hp.stf.module.dao.DemoDao;
import com.hp.stf.module.entity.Demo;

public class Demo2Service extends BaseService {
//	private static final Logger logger = Logger.getLogger(DemoService.class);

	@Resource
	DemoDao demoDao;
	
	public void update(Demo demo) {
		demoDao.update(demo);
	}
}
