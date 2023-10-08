package com.github.goomon.boot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BootApplication {

	public static void main(String[] args) {
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("front-controller", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// authentication, security, multi-lang, commons, ...
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
						String name = req.getParameter("name");

						resp.setStatus(HttpStatus.OK.value());
						resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println("Hello " + name);
					} else if (req.getRequestURI().equals("/users") && req.getMethod().equals(HttpMethod.GET.name())) {
						//
					} else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
				}
			}).addMapping("/*");
        });

		// Start tomcat servlet container.
		webServer.start();
	}

}
