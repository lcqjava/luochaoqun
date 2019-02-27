package com.luochaoqun.tx.chinabank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description:
 * @author: 小艾亲亲
 * @date: 2018年12月27日 上午9:33:40
 * @today:
 */

@EnableTransactionManagement
@MapperScan("com.luochaoqun.tx.chinabank.mapper")
//@EnableAutoConfiguration
@SpringBootApplication
public class ChinaBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChinaBankApplication.class, args);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("#####shutdown############");
			}
		}));
	}
}
