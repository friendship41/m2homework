package com.friendship41.m2homework.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
  private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
      "classpath:/",
      "classpath:/static/",
      "classpath:/templates/"
  };

  @Override
  public void addViewControllers(final ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("redirect:/login");
    registry.setOrder(Ordered.HIGHEST_PRECEDENCE+1);
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
  }
}
