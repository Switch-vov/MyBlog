package com.myblog.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myblog.domain.User;
import com.myblog.service.impl.UserService;
import com.myblog.service.inter.UserServiceInter;

public class TestMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		TestService testService = (TestService) applicationContext.getBean("testService");
		System.out.println(testService.getName());
		
		UserServiceInter userService = (UserServiceInter) applicationContext.getBean("userService");
		User user = userService.getUserById(10000);
		System.out.println(user.getUserName());
	}
}
