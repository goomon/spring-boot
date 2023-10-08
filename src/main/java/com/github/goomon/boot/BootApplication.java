package com.github.goomon.boot;

import com.github.goomon.boot.controller.HelloController;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
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
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(HelloController.class);
		applicationContext.refresh();

		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {

			HelloController helloController = applicationContext.getBean(HelloController.class);

			servletContext.addServlet("front-controller", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// authentication, security, multi-lang, commons, ...
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
						String name = req.getParameter("name");

						// Binding with controller.
						String ret = helloController.hello(name);

						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(ret);
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
