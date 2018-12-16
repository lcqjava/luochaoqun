package com.luochaoqun.hole;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2018年12月15日 下午1:34:41 
 * @today: 
 */
public class SimpleDateFormatMultiThread2 {

	/**
	 * sdf 继承DateFormat，其中有一个calendar属性，多线程环境下共同持有同一个SimpleDateFormat实例
	 * 复现步骤如下：
	 *    1.线程1调用了format方法，改变了calendar值
	 *    2.线程1中断
	 *    3.线程2开始执行，并改变了calendar值
	 *    4.线程2中断
	 *    5.线程1回来，calendar不是自己设置的值了
	 *    
	 * 解决办法1：不要让多个线程拥有同一个实例，在format中创建新的实例，并发量大的情况下会不短的创建实例，浪费资源
	 * 
	 * 解决办法2：同步SimpleDateFormat,在并发量大的情况下，影响性能
	 * 
	 * 解决办法3：使用ThreadLocal，每个线程复制一份SimpleDateFormat
	 */
	private static final ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
		
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		
	};
	
	public static String formatDate(Date date){
		return threadLocal.get().format(date);
	}
	
	public static Date parse(String strDate) throws ParseException{
		return threadLocal.get().parse(strDate);
	}
	
	
	
	
}
