package net.slipp.support;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(urlPatterns={"/*"})
public class CharacterEncodingFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(CharacterEncodingFilter.class);

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		logger.debug("character encoding filter init!");
		//System.out.println("charater encoding filter init!");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		chain.doFilter(req, resp);
	
	}

	@Override
	public void destroy() {
		
	}
}