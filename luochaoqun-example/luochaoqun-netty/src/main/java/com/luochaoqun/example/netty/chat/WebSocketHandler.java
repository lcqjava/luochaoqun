package com.luochaoqun.example.netty.chat;

import java.time.LocalDateTime;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年2月24日 上午12:51:18 
 * @today: 
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	    @Override
	    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
	        Channel channel = ctx.channel();
	        System.out.println(channel.remoteAddress() + ": " + msg.text());
	        ctx.channel().writeAndFlush(new TextWebSocketFrame("来自服务端: " + LocalDateTime.now()));
	    }
	    
	    @Override
	    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
	        System.out.println("ChannelId" + ctx.channel().id().asLongText());
	    }
	    
	    @Override
	    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
	        System.out.println("用户下线: " + ctx.channel().id().asLongText());
	    }
	    
	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	        ctx.channel().close();
	    }
}
