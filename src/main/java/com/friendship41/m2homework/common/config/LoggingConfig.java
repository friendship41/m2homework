package com.friendship41.m2homework.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoggingConfig {
  @Bean
  public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
    CommonsRequestLoggingFilter commonsRequestLoggingFilter = new CommonsRequestLoggingFilter();
    commonsRequestLoggingFilter.setIncludeClientInfo(true);
    commonsRequestLoggingFilter.setIncludeHeaders(true);
    commonsRequestLoggingFilter.setIncludePayload(true);
    commonsRequestLoggingFilter.setIncludeQueryString(true);
    commonsRequestLoggingFilter.setMaxPayloadLength(1024);
    return commonsRequestLoggingFilter;
  }
}
