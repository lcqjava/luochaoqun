package com.luochaoqun.test.concurrent;
/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: synchronized和reentrance区别
 *              1.synchronized粒度太大了，不需要手动释放，容易产生死锁，分为同步代码块和同步方法
 *              2.Reentrance可重入锁，可以分为读写锁，需要手动释放，提供更多的api，有以下几种场景使用Reentrance：
 *                1）设置公平锁和非公平锁：synchronized是非公平锁
 *                2）提供condition类，分组唤醒线程，如：ArrayBlockingQueue的notFull,notEmpty
 *                3）获取锁设置超时时间
 * @author: 小艾亲亲     
 * @date:   2019年3月7日 上午10:53:52 
 * @today: 
 */
public class SynchronizedReentranceCompare {
	
	
}
