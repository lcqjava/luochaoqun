package com.luochaoqun.self.frame.spring.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月10日 下午10:27:08 
 * @today: 
 */
public class Startup {

	public static void main(String[] args) {
		
		//InputStream inputStream = Startup.class.getClassLoader().getResourceAsStream("application.yml");
		InputStream inputStream = Startup.class.getClassLoader().getResourceAsStream("application.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
			System.out.println("#####"+properties.get("server.port"));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
//		Server server = new Server(8080);
//		ServletContextHandler contextHandler = new ServletContextHandler(server,"/");
//		server.setHandler(contextHandler);
//		
//		contextHandler.addServlet(TestHandler.class, "/xxx");
//		
//		try {
//			server.start();
//			server.join();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
	
}




