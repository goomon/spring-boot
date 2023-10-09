package com.github.goomon.spring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "com.github.goomon.spring.config.autoconfig.DispatcherServletConfig",
                "com.github.goomon.spring.config.autoconfig.TomcatWebServerConfig",
        };
    }
}
