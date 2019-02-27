package com.luochaoqun.javacore.designpattern.structCategory;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年2月19日 下午11:55:54 
 * @today: 
 */
public class AdapterPattern {

	public static void main(String[] args) {
		A a = new AdapterA();
		a.operationA();
	}

}

/**
 * 1.接口有7个方法，如果是实现它就必须重写7个方法，可实际是我只会用到2个，所以再定义一个抽象类，
 *   继承接口重写其中5个方法，然后继承抽象类实现接口只需要重写两个方法即可
 * @author chaoqunluo
 *
 */
interface Phone{
	void takePhoto();
	void playGame();
	void call();
	void sendMsg();
	void sendMail();
}

abstract class XiaomiPhone implements Phone{
	public void sendMsg(){
		
	}
	public void sendMail(){}
}

class PlayPhone extends XiaomiPhone implements Phone{

	@Override
	public void takePhoto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void call() {
		// TODO Auto-generated method stub
		
	}
	
}

/**
 * 2.类适配器:当需要实现接口A，但是A中没有我们想要的方法，在接口B中有这个方法。
 * 这种情况，可以定义一个适配器P来进行中转，这个适配器要实现A这样就能继续访问接口A中
 * 的方法，然后在继承接口B的实现类，这样
 */
interface A{
	void operationA();
}

interface B{
	void operationB();
}

class Bb implements B{

	@Override
	public void operationB() {
		System.out.println("BB");
	}
	
}
class AdapterA extends Bb implements A{

	@Override
	public void operationA() {
		operationB();
	}
	
}

/**
 * 3.对象适配器:就是要实现接口A，然后接口A中没有自己想要的方法，这是接口B中有提供，
 *   在适配器中定义一个私有属性B，并提供一个构造器赋值B，同时实现A，在调用A的方法时，
 *   用私有属性调用B的方法
*/
class ObjectAdapter implements A{

	private B b;
	public ObjectAdapter(B b) {
		this.b = b;
	}
	
	@Override
	public void operationA() {
		b.operationB();
	}
	
}
