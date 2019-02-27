package com.luochaoqun.javacore.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年1月11日 上午11:42:08 
 * @today: 
 */
public class JdkProxy implements InvocationHandler{

	private Object targetInstance;
	
	public Object newProxy(Object targetInstance) {
		this.targetInstance = targetInstance;
		return Proxy.newProxyInstance(targetInstance.getClass().getClassLoader(), targetInstance.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		boolean result = (boolean) method.invoke(targetInstance, args);
		if(result){
			result = boyFriendSuggest();
		}
		
		return result;
	}
	
	private boolean boyFriendSuggest(){
		System.out.println("乖，听话，咱不买");
		return false;
	}

}
