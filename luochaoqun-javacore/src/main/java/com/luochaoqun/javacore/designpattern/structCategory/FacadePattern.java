package com.luochaoqun.javacore.designpattern.structCategory;
/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 官方的解释是：为子系统提供一个一致的界面，定义一个高层接口，使得子系统更加容易调用
 *               通俗解释是：有多个子系统，定义一个方法依次调用子系统
 * @author: 小艾亲亲     
 * @date:   2019年2月20日 下午11:02:46 
 * @today: 
 */
public class FacadePattern {

	public static void cook(){
		Rice rice = new Rice();
		Oil oil = new Oil();
		Meet meet = new Meet();
	}
	
	public static void main(String[] args) {
		cook();
	}
	
}

class Rice{
	public Rice(){
		System.out.println("大米准备好了");
	}
}

class Meet{
	public Meet(){
		System.out.println("不管，我要吃肉肉");
	}
}

class Oil{
	public Oil(){
		System.out.println("炒菜需要有");
	}
}
