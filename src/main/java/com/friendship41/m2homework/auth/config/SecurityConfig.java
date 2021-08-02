package com.friendship41.m2homework.auth.config;

import com.friendship41.m2homework.auth.data.type.RoleType;
import com.friendship41.m2homework.auth.filter.AuthFilter;
import com.friendship41.m2homework.auth.service.AuthProvider;
import com.friendship41.m2homework.auth.service.LoginFailureHandler;
import com.friendship41.m2homework.auth.service.LoginSuccessHandler;
import com.friendship41.m2homework.member.service.MemberService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final MemberService memberService;
  private final UserDetailsService userDetailsService;
  private final CommonsRequestLoggingFilter commonsRequestLoggingFilter;

  private final String[] URL_WHITELIST = {
      "/login/**", "/static/**", "/templates/**", "/register"
  };

  @Override
  public void configure(final WebSecurity web) throws Exception {
    web.ignoring()
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers(URL_WHITELIST).permitAll()
        .antMatchers("/member/**").hasAnyRole(RoleType.MEMBER.name(), RoleType.GUILD_MASTER.name(), RoleType.ADMIN.name())
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login/page")
        .permitAll()
        .and()
        .logout().permitAll()
        .and()
        .addFilterBefore(this.authFilter(), UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(this.commonsRequestLoggingFilter, AuthFilter.class)
        .csrf().disable();
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(this.authProvider());
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder(4);
  }

  @Bean
  public AuthFilter authFilter() throws Exception {
    AuthFilter authFilter = new AuthFilter(authenticationManager());
    authFilter.setFilterProcessesUrl("/login");
    authFilter.setAuthenticationSuccessHandler(this.loginSuccessHandler());
    authFilter.setAuthenticationFailureHandler(this.loginFailureHandler());
    authFilter.afterPropertiesSet();
    return authFilter;
  }

  @Bean
  public LoginSuccessHandler loginSuccessHandler() {
    return new LoginSuccessHandler(this.memberService);
  }

  @Bean
  public LoginFailureHandler loginFailureHandler() {
    return new LoginFailureHandler();
  }

  @Bean
  public AuthProvider authProvider() {
    return new AuthProvider(this.userDetailsService, this.bCryptPasswordEncoder());
  }
}
