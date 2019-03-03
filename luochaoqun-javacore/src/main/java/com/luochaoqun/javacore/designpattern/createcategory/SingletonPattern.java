package com.luochaoqun.javacore.designpattern.createcategory;

import org.junit.validator.ValidateWith;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description:双重检查锁
 * @author: 小艾亲亲
 * @date: 2019年2月19日 下午9:03:41
 * @today:
 */
public class SingletonPattern {

	private static volatile SingletonPattern instance;

	private SingletonPattern() {

	}

	public static SingletonPattern getInstance() {
		if (instance == null) {
			synchronized (SingletonPattern.class) {
				if (instance == null) {
					instance = new SingletonPattern();
				}
			}
		}
		return instance;
	}

}
