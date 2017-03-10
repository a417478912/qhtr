package qhtr.test;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qhtr.model.User;
import com.qhtr.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
//配置了@ContextConfiguration注解并使用该注解的locations属性指明spring和配置文件之后，
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class MyBatisTestBySpringTestFramework {

    //注入userService
    @Resource
    private UserService userService;
    
    @Test
    public void testAddUser() throws Exception{
    	 User user = new User();
         user.setId(7);
         user.setName("我就不信你能插入111");
         user.setCreateTime(new Date());
        
        User user1 = new User();
        user1.setName("我就不信你能插入2222");
        user1.setCreateTime(new Date());
    	List<User> users = new ArrayList<User>();
    	users.add(user1);
      // userService.addUser(user);
    }
    

    public void testGetUserById(){
        User user = userService.getUserById(333);
        System.out.println(user.getName());
    }
}