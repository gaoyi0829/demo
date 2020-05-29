package com.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect //声明切面 自动完成织入的操作
public class ServiceAdvice { //创建服务层的通知类

	//针对于 addUser这个执行方法 ,创建相应的Aop操作
	//@After("com.service.UserService.addUser()")
	public void afterAddUser(){
		System.out.println("执行一个User对象的插入之后的Advice--后置通知");
	}
	/**
	 * 通知的编写
	 * 		@Before @After @AfterThrowing 书写的格式同上
	 */

	//重点操作 环绕的书写
	@Around("com.service.UserService.addUser()")
	public Object addUserAroundAdvice(ProceedingJoinPoint p) throws Throwable{
		System.out.println("环绕的前置");
		Object result = p.proceed();
		System.out.println("环绕的后置");

		return result;

	}

}
