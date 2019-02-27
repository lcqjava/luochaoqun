package com.luochaoqun.javacore.designpattern.createcategory;
/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年2月19日 下午11:50:54 
 * @today: 
 */
public class FactoryPattern {

	public static Object instanceFactory(String param){
		if(param.equals("a")){
			return new Apple();
		}else if(param.equals("b")){
			return new Banana();
		}else if(param.equals("c")){
			return new Orange();
		}
		
		return null;
	}
	
}

class ApplePlus{
	
}

class OrangePlus{
	
}

class BananaPlus{
	
}