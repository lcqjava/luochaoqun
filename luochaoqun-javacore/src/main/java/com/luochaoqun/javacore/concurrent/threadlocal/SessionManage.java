package com.luochaoqun.javacore.concurrent.threadlocal;

import java.io.Serializable;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月5日 下午1:06:17 
 * @today: 
 */
public class SessionManage extends Thread{

	private static ThreadLocal<SessionUser> threadLocal = new ThreadLocal<SessionUser>();
	
	public void run(){
		threadLocal.get();
	}
	
	
}

class SessionUser implements Serializable{
	
	private static final long serialVersionUID = -8178023804141099009L;
	
	private String tenantId;
	private String token;
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}