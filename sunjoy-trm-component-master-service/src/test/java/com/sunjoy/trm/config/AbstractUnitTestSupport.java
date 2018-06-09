package com.sunjoy.trm.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.support.GenericWebApplicationContext;

import com.sunjoy.common.exception.CommonException;
import com.sunjoy.framework.client.core.ClientConfig;

/**
 * 测试单元的统一配置
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { ClientConfig.class })
public abstract class AbstractUnitTestSupport {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	// @Autowired
	// private SuperviserServiceImpl superviserServiceImpl;

	protected MockMvc mockMvc;

	@Autowired
	private GenericWebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		logger.debug(wac.getApplicationName());

	}

	protected void handleException(Exception e) {
		logger.debug("handleException:", e);
		if (e instanceof CommonException) {
			String code = ((CommonException) e).getCode();
			Assert.assertTrue(code.contains("00001")// 授权信息为空
					|| code.contains("00400:")// 调用服务不存在
					|| code.contains("404")// 调用服务不存在
					|| code.contains("CM057")// 未发卡
					|| code.contains("00099"));// 网络连接失败

		} else {
			Assert.assertFalse(true);
		}
	}
}
