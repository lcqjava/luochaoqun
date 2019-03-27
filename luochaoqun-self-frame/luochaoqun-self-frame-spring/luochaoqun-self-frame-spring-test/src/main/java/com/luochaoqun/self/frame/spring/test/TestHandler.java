package com.luochaoqun.self.frame.spring.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月10日 下午10:29:49 
 * @today: 
 */
public class TestHandler extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7193503403967241324L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject ret =  new JSONObject();
        try {
            String ttsTxt = req.getParameter("text");
            String outFile = System.nanoTime() + ".mp4";
            ret.put("ret","0");
        }catch (Exception ex){
            ret.put("ret","-1");
            ret.put("error",ex.getMessage());
        }
        if(req.getParameter("callback")!=null) {
            resp.getWriter().write(req.getParameter("callback")+"("+ret.toString()+")");
        }else {
            resp.getWriter().write(ret.toString());
        }
    }
}
