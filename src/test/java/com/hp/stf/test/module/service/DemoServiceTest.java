package com.hp.stf.test.module.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.hp.stf.module.entity.Demo;
import com.hp.stf.module.service.DemoService;
import com.hp.stf.module.vo.DemoVo;
import com.hp.stf.test.BaseTest;

@TransactionConfiguration(transactionManager="txManager", defaultRollback=true)
public class DemoServiceTest extends BaseTest {
	private static final Logger logger = Logger.getLogger(DemoServiceTest.class);

	@Resource
	DemoService demoService;
	
	@Test
	public void testSaveDemo() {
		logger.debug("------------------------> testSaveDemo 1 <-------------------------");		
		DemoVo demoVo = new DemoVo();
		demoVo.setPassword("123456");
		demoVo.setUser("Jacky");
		demoVo.setUsername("zhujian");
		try {
			demoService.log(demoVo);
		} catch (Throwable e) {
			logger.debug("------------------------> catched Exception <-------------------------");					
			logger.debug(e.getMessage(), e);
		}
		logger.debug("------------------------> testSaveDemo 1 <-------------------------");		
		
		
		logger.debug("------------------------> testSaveDemo 2 <-------------------------");
		Demo d = demoService.get("zhujian");
		logger.debug("------>[" + d + "]");
		logger.debug("------------------------> testSaveDemo 2 <-------------------------");		
	}
}
