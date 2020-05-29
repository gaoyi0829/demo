package com.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value="school_1")
public class School { //创建一个新的组件
	@Value("清华大学")
	private String name;public School() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "School [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
