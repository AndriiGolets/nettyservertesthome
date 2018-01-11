package netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
public class TCPServer {

	@Autowired
	private ServerBootstrap b;
	
	@Autowired
	private InetSocketAddress tcpPort;

	private ChannelFuture serverChannelFuture;

	//@PostConstruct
	public void start() throws Exception {
		System.out.println("Starting server at " + tcpPort);
		serverChannelFuture = b.bind(tcpPort).sync();
	}

	@PreDestroy
	public void stop() throws Exception {
	    serverChannelFuture.channel().closeFuture().sync();
	}

}
