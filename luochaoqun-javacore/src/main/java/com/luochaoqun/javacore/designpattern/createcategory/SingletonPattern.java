package com.luochaoqun.javacore.designpattern.createcategory;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description:
 * @author: 小艾亲亲
 * @date: 2019年2月19日 下午9:03:41
 * @today:
 */
public class SingletonPattern {

	private static SingletonPattern instance;

	private SingletonPattern() {

	}

	public static SingletonPattern getInstance() {
		synchronized (instance) {
			if (instance == null) {
				synchronized (instance) {
					if (instance == null) {
						instance = new SingletonPattern();
					}
				}
			}
		}
		return instance;
	}

}
