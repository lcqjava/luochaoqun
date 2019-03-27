package com.luochaoqun.self.frame.spring.test.enums;
/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月10日 下午11:20:15 
 * @today: 
 */
public enum ApplicationConfigEnum {

	SERVER_NAME("server.name"),
	SERVER_PORT("server.port");
	
	private String configName;
	
	private ApplicationConfigEnum(String configName){
		this.configName = configName;
	}
	
	public String getConfigName(){
		return configName;
	}
	
}
