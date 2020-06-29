package com.vrush.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer, Filter {

    @Autowired
    private ResourceProperties resourceProperties;
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println("WebConfig; "+request.getRequestURI());
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Expose-Headers", "USERID");
        response.addHeader("Access-Control-Expose-Headers", "ROLE");
        response.addHeader("Access-Control-Expose-Headers", "responseType");
        response.addHeader("Access-Control-Expose-Headers", "observe");
        System.out.println("Request Method: "+request.getMethod());
        if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
            try {
                chain.doFilter(req, res);
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Pre-flight");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers"+"Authorization, content-type," +
                    "USERID"+"ROLE"+
                    "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType,observe");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        final String[] staticLocations = resourceProperties.getStaticLocations();

        final String[] indexLocations = new String[staticLocations.length];

        for (int i = 0; i < staticLocations.length; i++) {
            indexLocations[i] = staticLocations[i] + "index.html";
        }

        registry.addResourceHandler("/**/*.css", "/**/*.html", "/**/*.js", "/**/*.json", "/**/*.bmp", "/**/*.jpeg",
                "/**/*.jpg", "/**/*.png", "/**/*.ttf", "/**/*.eot", "/**/*.svg", "/**/*.woff", "/**/*.woff2")
                .addResourceLocations(staticLocations);
        registry.addResourceHandler("/**").addResourceLocations(indexLocations)
                .resourceChain(true).addResolver(new PathResourceResolver() {
            @Override
            protected Resource getResource(String resourcePath, Resource location) throws IOException {
                return location.exists() && location.isReadable() ? location : null;
            }
        });
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "index.html");
    }
}