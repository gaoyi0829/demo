package com.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value="school_1")
public class School { //����һ���µ����
	@Value("�廪��ѧ")
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
