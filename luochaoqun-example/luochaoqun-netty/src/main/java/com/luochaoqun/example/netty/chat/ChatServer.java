package com.luochaoqun.example.netty.chat;

import java.util.List;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description:
 * @author: 小艾亲亲
 * @date: 2019年2月21日 下午9:58:57
 * @today:
 */
public class ChatServer {

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
            				  .channel(NioServerSocketChannel.class)
                           //.handler(new LoggingHandler(LogLevel.INFO))
                           .childHandler(new WebSocketChannelInitializer());
            
            ChannelFuture channelFuture = serverBootstrap.bind(8989).sync();
            channelFuture.channel().closeFuture().sync();
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
	}
}
