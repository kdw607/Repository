package net.slipp;

import java.io.File;

import javax.servlet.ServletException;

import net.slipp.support.CharacterEncodingFilter;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServletLauncher {

	private static final Logger logger = LoggerFactory.getLogger(WebServletLauncher.class);
	
	public static void main(String[] args) throws ServletException, LifecycleException {
		
		logger.debug("character encoding filter init!");
		
		String webappDirLocation = "webapp/";
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8181);
		
		tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
		
		tomcat.start();
		tomcat.getServer().await();
	}
}