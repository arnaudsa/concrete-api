package com.concrete;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfigTest.class })
public class SpringTestInit {

	private static final Logger log = Logger.getLogger(SpringTestInit.class);

	@Autowired
	private DataSource dataSource;

	@Test
	public void test() {

		Assert.assertNotNull(dataSource);

		log.info("*************************************");
		log.info("Spring inicializado com sucesso");
		log.info("*************************************");
	}
}
