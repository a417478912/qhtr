package qhtr.test;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qhtr.model.User;
import com.qhtr.service.UserService;

public class MyBatisTest {
 
    private UserService userService;
	private ApplicationContext ac;
     
     /**
      * 这个before方法在所有的测试方法之前执行，并且只执行一次
      * 所有做Junit单元测试时一些初始化工作可以在这个方法里面进行
      * 比如在before方法里面初始化ApplicationContext和userService
      */
     @Before
     public void before(){
         ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
         //从Spring容器中根据bean的id取出我们要使用的userService对象
         userService = (UserService) ac.getBean("userService");
     }
     
     @Test
     public void testAddUser() throws Exception{
         //ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
         //UserServiceI userService = (UserServiceI) ac.getBean("userService");
         User user = new User();
         user.setId(111);
         user.setName("白虎神皇xdp");
         user.setCreateTime(new Date());
         userService.addUser(user);
     }
     
 }