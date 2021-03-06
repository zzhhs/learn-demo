//package com.example.demo.secutiry.config;
//
////import com.example.demo.secutiry.handler.MyAuthenticationFailureHandler;
////import com.example.demo.secutiry.handler.MyAuthenticationSucessHandler;
//import lombok.Setter;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
////    @Autowired
////    protected ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;
////    @Autowired
////    protected RemoteTokenServices remoteTokenServices;
////    @Autowired
////    protected UserDetailsService userDetailsService;
////    @Autowired
////    private PermitAllUrlProperties permitAllUrlProperties;
////    @Autowired
////    private RestTemplate lbRestTemplate;
////    @Setter
////    private boolean details;
////
////
////    /**
////     * 默认的配置，对外暴露
////     *
////     * @param httpSecurity
////     */
////    @Override
////    @SneakyThrows
////    public void configure(HttpSecurity httpSecurity) {
////        //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
////        httpSecurity.headers().frameOptions().disable();
////        ExpressionUrlAuthorizationConfigurer<HttpSecurity>
////                .ExpressionInterceptUrlRegistry registry = httpSecurity
////                .authorizeRequests();
////        permitAllUrlProperties.getIgnoreUrls()
////                .forEach(url -> registry.antMatchers(url).permitAll());
////        registry.anyRequest().authenticated()
////                .and().csrf().disable();
////    }
////
////
////
////    /**
////     * 不获取用户详细 只有用户名
////     *
////     * @param resources
////     */
////    protected void notGetUser(ResourceServerSecurityConfigurer resources) {
////        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
////        DefaultUserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter();
////        accessTokenConverter.setUserTokenConverter(userTokenConverter);
////
////        remoteTokenServices.setRestTemplate(lbRestTemplate);
////        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
////        resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
////                .tokenServices(remoteTokenServices);
////    }
////
////
////    /**
////     * 上下文中获取用户全部信息，两次调用userDetailsService，影响性能
////     *
////     * @param resources
////     */
////    private void canGetUser(ResourceServerSecurityConfigurer resources) {
////        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
////        DefaultUserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter();
////        userTokenConverter.setUserDetailsService(userDetailsService);
////        accessTokenConverter.setUserTokenConverter(userTokenConverter);
////
////        remoteTokenServices.setRestTemplate(lbRestTemplate);
////        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
////        resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
////                .tokenServices(remoteTokenServices);
////    }
//
//
////    @Autowired
////    private MyAuthenticationSucessHandler authenticationSucessHandler;
////    @Autowired
////    private MyAuthenticationFailureHandler authenticationFailureHandler;
//
////    @Override
////    public void configure(HttpSecurity http) throws Exception {
////        http.formLogin() // 表单登录
////                .loginProcessingUrl("/login") // 处理表单登录 URL
////                //.successHandler(authenticationSucessHandler) // 处理登录成功
////                //.failureHandler(authenticationFailureHandler) // 处理登录失败
////                .and()
////                .authorizeRequests() // 授权配置
////                .anyRequest()  // 所有请求
////                .authenticated() // 都需要认证
////                .and()
////                .csrf().disable();
////    }
//
////    @Override
////    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
////    }
//}