package com.hp.stf.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager", defaultRollback=true)
@Transactional(rollbackFor={Throwable.class})
@TestExecutionListeners( {STFDependencyInjectionTestExecutionListener.class
	                    , STFDirtiesContextTestExecutionListener.class
	                    , STFTransactionalTestExecutionListener.class} )
public class BaseTest {
	@Test
	public void test() {}
}
