package com.hp.stf.module.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.hp.stf.module.dao.DemoDao;
import com.hp.stf.module.entity.Demo;
import com.hp.stf.module.utils.log.LoggerUtility;
import com.hp.stf.module.vo.DemoVo;

public class DemoService extends BaseService {

	private static final Logger logger = Logger.getLogger(DemoService.class);
	private static final LoggerUtility loggerUtility = LoggerUtility.getInstance();

	@Resource
	DemoDao demoDao;
	
	@Resource
	Demo2Service demo2Service;

	public void log(DemoVo demoVo) {
		loggerUtility.startPerformanceLog("DemoService.get()");
		Demo demo = get(demoVo.getUsername() );
		loggerUtility.endPerformanceLog();
		logger.debug("demo ----> [" + demo + "]");
		
		Demo demoToSave = new Demo();
		demoToSave.copy(demo);
		demoToSave.setUsername("test1");
		saveDemo(demoToSave);
		
		demo.setPassword("777777");
		demo2Service.update(demo);
	}
	
	public void update(Demo demo) {
		loggerUtility.startInvoke("update");
		demoDao.update(demo);
		loggerUtility.endInvoke("update");
	}
	
	private Demo saveDemo(Demo demo) {
		loggerUtility.startInvoke("saveDemo");
		demoDao.save(demo);
		loggerUtility.endInvoke("saveDemo");
		return demo;
	}
	
	public Demo get(String username) {
		return (Demo)demoDao.get(username);
	}
}
