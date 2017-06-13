package cn.janescott;

import cn.janescott.service.SendEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZoneApplicationTests {

	@Autowired
	private SendEmailService sendEmailService;

	@Test
	public void contextLoads() {

	}

	@Test
	public void testSend(){
		sendEmailService.send("测试方法", "这是测试方法的邮件");
	}
}


