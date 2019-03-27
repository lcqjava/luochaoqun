package com.xiaoaiqinqin.instance.message.xiaoai.chat.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description:
 * @author: 小艾亲亲
 * @date: 2019年3月19日 下午7:54:22
 * @today:
 */
@Component
public class XiaoaiChatServer {

	private static class SingletonXiaoaiChatServer {
		static final XiaoaiChatServer INSTANCE = new XiaoaiChatServer();
	}

	public static XiaoaiChatServer getInstance() {
		return SingletonXiaoaiChatServer.INSTANCE;
	}

	private final Integer port = 13001;

	private EventLoopGroup bossEventLoopGroup;
	private EventLoopGroup workerEventLoopGroup;
	private ServerBootstrap serverBootstrap;
	private ChannelFuture channelFuture;
	private Integer bossThreadNum = 2;
	private Integer workerThreadNum = 4;

	public XiaoaiChatServer() {
		// 监控链接,accept
		bossEventLoopGroup = new NioEventLoopGroup(bossThreadNum);
		// 处理io
		workerEventLoopGroup = new NioEventLoopGroup(workerThreadNum);

		try {
			serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossEventLoopGroup, workerEventLoopGroup).channel(NioServerSocketChannel.class);
			// .childHandler(new WebSocketChannelHandler());

			ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {

		}
	}
	
	public static void main(String[] args) {
		String[] arr = {"A","B","C"};
		List<String> list = Arrays.asList(arr);
		list.add("A");
		
		list.remove(0);
		System.out.println(arr[0]);
		
	}

}
