package com.hm.common.netty;

import com.hm.common.exception.ErrorCode;
import com.hm.common.exception.ServiceException;
import com.hm.common.util.CommonUtil;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author shishun.wang
 * @date 下午2:23:39 2017年6月27日
 * @version 1.0
 * @describe
 */
public class NettyFactory {

	/**
	 * @author shishun.wang
	 * @date 下午3:52:55 2017年6月27日
	 * @version 1.0
	 * @describe 
	 */
	public static class SampleServer {

		public static void start(String host, int port, ChannelHandlerAdapter handlerAdapter) throws Exception {
			if (CommonUtil.isAnyEmpty(host, port)) {
				throw ServiceException.warning(ErrorCode.REQUIRED_PARAMETERS_MISSING);
			}

			// 配置服务端的NIO线程组
			EventLoopGroup bossGroup = new NioEventLoopGroup();
			EventLoopGroup workerGroup = new NioEventLoopGroup();
			try {
				ServerBootstrap bootstrap = new ServerBootstrap();
				bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
						.childHandler(new ChannelInitializer<SocketChannel>() {
							@Override
							public void initChannel(SocketChannel ch) throws Exception {
								ch.pipeline().addLast(handlerAdapter);
							}
						})// .option(ChannelOption.SO_BACKLOG, 128)
							// //最大客户端连接数为128
						.childOption(ChannelOption.SO_KEEPALIVE, true);
				// 绑定端口，同步等待成功
				ChannelFuture future = bootstrap.bind(port).sync();
				// 等待服务端监听端口关闭
				future.channel().closeFuture().sync();
			} finally {
				// 优雅退出，释放线程池资源
				workerGroup.shutdownGracefully();
				bossGroup.shutdownGracefully();
			}
		}
	}
}
