package com.luochaoqun.javacore.designpattern.structCategory;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 站在巨人的肩膀上，让自己看的更远--牛顿
 *               定义一个接口A和操作a，另一个类B去实现它，再定义一个类C实现A同时B作为C的一个属性,这样c方法
 *               可以调用B提供的a方法基础上再实现新的功能，还可以继续定义D也是提供了属性C。。。
 *               如io流：
 * @author: 小艾亲亲     
 * @date:   2019年2月20日 下午9:56:29 
 * @today: 
 */
public class DecoratorPattern {

	public static void main(String[] args) {
		Annimal annimal = new BeautifulGirl(new Person());
		annimal.eat();
	}
	
}

interface Annimal{
	void eat();
}

class Person implements Annimal{

	@Override
	public void eat() {
		System.out.println("我会吃");
	}
	
}

abstract class Girl implements Annimal{
	
	protected Person person;
	public Girl(Person person){
		this.person = person;
	}
	
	public void eat(){
		person.eat();
		System.out.println("我会买包包");
	}
	
}

class BeautifulGirl extends Girl{


	public BeautifulGirl(Person person) {
		super(person);
	}

	@Override
	public void eat() {
		super.eat();
		System.out.println("越漂亮的女人越会骗人");
	}
	
	public void play(){
		System.out.println("我还会玩");
	}
	
}