package com.vo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;



/**
 * 	Component : 组件 交由Spring管理的对象
 *  与Component注解功能一致,代表的含义不同 还有3个
 *  	@Repository: 注解在DAO实现对象上
 *  	@Service：注解在Service类上
 *  	@Controller 注解在控制类上
 */
@Component(value="s1")  //声明一个组件,在没有特别的声明时 创建一个类名小写的对象
@Scope(value="prototype")   //单例模式 singletion
public class Student { //创建一个最简单的java
	@Value("张三")
	private String name;
	@Value("18")
	private int age;

	//使用注解给对象赋值 --存在两种方式 byName根据声明的对象的名字id 还有就是byType
	//@Autowired //byType方式的注解式注入
	/**
	 * 1.该类型的对象 在Spring的容器中只能出现一个
	 */
	//@Qualifier(value="school_1") //byName的方式的注入 @Autowired和@Qualifier必须连用

	@Resource(name="school_1")  //bytype和byname的综合使用

	private School school;


	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public Student() {
		// TODO Auto-generated constructor stub
	}
	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", school=" + school
				+ "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	//在要管理的组件中可以编写初始化方法 和销毁方法
	@PostConstruct
	public void initAfter(){
		System.out.println("当前Bean初始化完成");
	}
	@PreDestroy
	public void preDestroy(){
		System.out.println("当前Bean销毁之前");
	}



}


