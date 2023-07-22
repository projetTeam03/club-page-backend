package com.projet.clubpage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String uploadTagPath;

    public WebConfig(@Value("${custom.path.upload-tag}") String uploadTagPath) {
        this.uploadTagPath = uploadTagPath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");


        List<String> imageFolders = Arrays.asList("tag");
        for (String imageFolder : imageFolders) {
            registry.addResourceHandler("/static/" + imageFolder + "/**")
                    .addResourceLocations("file:///")
                    .setCachePeriod(3600)
                    .resourceChain(true)
                    .addResolver(new PathResourceResolver());
        }

    }
}
