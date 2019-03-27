package com.luochaoqun.test.concurrent;
/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: synchronized this/Object/.class的区别
 *              1.this:只有一把钥匙的多把锁，一个类里有多个同步代码块/同部分方法，同一时间只能进入一个
 *              2.object:每个对象都相当于一把锁，不同的锁有不同的钥匙。例如：object是方法里的局部变量，
 *                       每个线程都有一把锁并且是不同的钥匙
 *              3.class:等同于静态同步代码块
 *              
 * @author: 小艾亲亲     
 * @date:   2019年3月7日 上午10:42:05 
 * @today: 
 */
public final class SynchronizedThisObjectClassCompare {

	
}
