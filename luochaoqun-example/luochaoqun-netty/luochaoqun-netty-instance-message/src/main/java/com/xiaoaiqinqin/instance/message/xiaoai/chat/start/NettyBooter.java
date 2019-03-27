package com.xiaoaiqinqin.instance.message.xiaoai.chat.start;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xiaoaiqinqin.instance.message.xiaoai.chat.server.XiaoaiChatServer;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月19日 下午7:56:40 
 * @today: 
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			XiaoaiChatServer
		}
	}

	
	
	
	
	
	
}
