package com.service;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
public class UserService { //创建一个服务类(针对于一个数据表-vo)


	//在一个实际的业务操作类中 ,触发Aop操作的 一定是一个执行的方法
	//声明切点
	@Pointcut("execution(* com.service.UserService.addUser())") //尽量的完整的书写
	public void addUser(){
		System.out.println("执行添加User的操作");
	}


}





