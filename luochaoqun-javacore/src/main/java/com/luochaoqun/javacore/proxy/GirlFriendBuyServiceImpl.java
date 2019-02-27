package com.luochaoqun.javacore.proxy;
/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年1月11日 上午11:43:50 
 * @today: 
 */
public class GirlFriendBuyServiceImpl implements BuyService{

	@Override
	public boolean buy(String goods) {
		System.out.println("哈尼，这包好好看哟，帮我买");
		return true;
	}

}
