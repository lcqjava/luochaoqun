package com.luochaoqun.javacore.designpattern.structCategory;
/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 对原来的实现类作为新类的一个属性，并且实现原来的接口，
 *               在调用属性的方法前和方法后执行自己的逻辑，对产生的结果进行控制
 *               
 * @author: 小艾亲亲     
 * @date:   2019年2月20日 下午10:56:13 
 * @today: 
 */
public class ProxyPattern {

}

interface Source {
	void method();
}
class OldClass implements Source{

	@Override
	public void method() {
		System.out.println("我是老方法");
	}
}
class Proxy implements Source{
	private Source source = new OldClass();

	void doSomethingBefore(){
		System.out.println("do something before");
	}
	void doSomethingAfter(){
		System.out.println("do something after");
	}
	
	@Override
	public void method() {
		doSomethingBefore();
		source.method();
		doSomethingAfter();
	}
	
	
}

