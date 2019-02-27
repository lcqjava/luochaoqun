package com.luochaoqun.tx.chinabank.config;

import java.util.Properties;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 事物
 * @author: 小艾亲亲     
 * @date:   2018年12月28日 下午2:30:53 
 * @today: 
 */
@Configuration
public class TransactionConfig {

	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@Bean(name="txAdvice")
	public TransactionInterceptor getAdvisor(){
		
		Properties properties = new Properties();
		// 查询
		properties.put("get*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
		properties.put("select*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
		properties.put("find*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
		properties.put("query*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
		properties.put("list*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
		properties.put("count*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
		
		// 增加
		properties.put("insert*", "PROPAGATION_REQUIRED,-Exception");
		properties.put("save*", "PROPAGATION_REQUIRED,-Exception");
		properties.put("add*", "PROPAGATION_REQUIRED,-Exception");
		
		// 删除
		properties.put("del*", "PROPAGATION_REQUIRED,-Exception");
		properties.put("remove*", "PROPAGATION_REQUIRED,-Exception");

		// 修改
		properties.put("update*", "PROPAGATION_REQUIRED,-Exception");
		
		return new TransactionInterceptor(transactionManager, properties);
	}
	
	@Bean
	public BeanNameAutoProxyCreator txProxy(){
		BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
		beanNameAutoProxyCreator.setInterceptorNames("txAdvice");
		beanNameAutoProxyCreator.setBeanNames("*Service","*ServiceImpl");
		beanNameAutoProxyCreator.setProxyTargetClass(true);
		return beanNameAutoProxyCreator;
		
	}
}
