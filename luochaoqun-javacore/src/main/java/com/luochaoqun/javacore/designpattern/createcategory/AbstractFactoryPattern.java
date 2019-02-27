package com.luochaoqun.javacore.designpattern.createcategory;
/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年2月18日 下午11:14:26 
 * @today: 
 */
public class AbstractFactoryPattern {
	
	private AbstractFactoryPattern(){
		
	}
	
	public static Food getApple(){
		return new Apple();
	}
	public static Food getOrange(){
		return new Orange();
	}
	public static Food getBanana(){
		return new Banana();
	}
	
}

interface Food{
	
}

class Apple implements Food{
	
}

class Banana implements Food{
	
}

class Orange implements Food{
	
}