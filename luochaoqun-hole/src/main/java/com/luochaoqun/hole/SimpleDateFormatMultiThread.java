package com.luochaoqun.hole;

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
public class SimpleDateFormatMultiThread {

	/**
	 * sdf 继承DateFormat，其中有一个calendar属性，多线程环境下共同持有同一个SimpleDateFormat实例
	 * 步骤如下：
	 *    1.线程1调用了format方法，改变了calendar值
	 *    2.线程1中断
	 *    3.线程2开始执行，并改变了calendar值
	 *    4.线程2中断
	 *    5.线程1回来，calendar不是自己设置的值了
	 *   
	 */
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String formatDate(Date date){
		return sdf.format(date);
	}
	
	public static Date parse(String strDate) throws ParseException{
		return sdf.parse(strDate);
	}
	
	
	
	
}
