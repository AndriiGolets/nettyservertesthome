package netty.server;


import netty.config.SpringConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		LOG.debug("Starting application context");

        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext (
				SpringConfig.class);
		ctx.registerShutdownHook();

		ctx.getBean (TCPServer.class).start ();
	}

}
