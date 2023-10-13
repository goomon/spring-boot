package com.github.goomon.spring.config.autoconfig;

import com.github.goomon.spring.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@MyAutoConfiguration
public class ServerPropertiesConfig {
    @Bean
    public ServerProperties serverProperties(Environment env) {
        ServerProperties props = new ServerProperties();
        props.setContextPath(env.getProperty("contextPath"));
        props.setPort(Integer.parseInt(env.getProperty("port")));
        return props;
    }
}
