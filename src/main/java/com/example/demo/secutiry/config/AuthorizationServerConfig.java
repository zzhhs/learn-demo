//package com.example.demo.secutiry.config;
//
//import lombok.SneakyThrows;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends WebSecurityConfigurerAdapter {
//
////    @Override
////    @SneakyThrows
////    protected void configure(HttpSecurity http) {
////        http
////                .requestMatchers().anyRequest()
////                //设置符合条件的端点通过，不被保护
////                .and().authorizeRequests().antMatchers("/oauth/*").permitAll();
//////        http
//////                .formLogin()
//////                .loginPage("/token/login")
//////                .loginProcessingUrl("/token/form")
//////                .and()
//////                .authorizeRequests()
//////                .antMatchers(
//////                        "/token/**",
//////                        "/actuator/**",
//////                        "/mobile/**").permitAll()
//////                .anyRequest().authenticated()
//////                .and().csrf().disable();
////                //.apply(mobileSecurityConfigurer());
////    }
////
////    /**
////     * 不拦截静态资源
////     *
////     * @param web
////     */
////    @Override
////    public void configure(WebSecurity web) {
////        web.ignoring().antMatchers("/css/**");
////    }
////
//
//    @Bean
//    @Override
//    @SneakyThrows
//    public AuthenticationManager authenticationManagerBean() {
//        return super.authenticationManagerBean();
//    }
//
//
//
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}