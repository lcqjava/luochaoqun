package com.luochaoqun.javacore.proxy;

import org.junit.Test;

import com.luochaoqun.javacore.proxy.jdkproxy.JdkProxy;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年1月11日 下午1:32:33 
 * @today: 
 */
public class ProxyTest {

	@Test
	public void testJdkProxy(){
		JdkProxy jdkProxy = new JdkProxy();
		BuyService buyService = (BuyService)jdkProxy.newProxy(new GirlFriendBuyServiceImpl());
		buyService.buy("LV");
		
	}
}
