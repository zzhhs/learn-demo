//package com.example.demo.secutiry.config;
//
//import com.example.demo.secutiry.SecurityConst;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
//import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@Slf4j
//@EnableAuthorizationServer
//public class AuthorizationServerConfigurerAdapterConfig extends AuthorizationServerConfigurerAdapter {
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//    @Autowired
//    private UserDetailService userDetailService;
//
//    @Autowired
//    private AuthenticationManager authenticationManagerBean;
//
//
////    @Override
////    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
////        security
////                .allowFormAuthenticationForClients()
////                .checkTokenAccess("isAuthenticated()");
////    }
//
//    /**
//     * 从数据库读取终端
//     * @param clients
//     * @throws Exception
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
//        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
//        clientDetailsService.setSelectClientDetailsSql(SecurityConst.DEFAULT_SELECT_STATEMENT);
//        clientDetailsService.setFindClientDetailsSql(SecurityConst.DEFAULT_FIND_STATEMENT);
//        clients.withClientDetails(clientDetailsService);
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                .tokenStore(tokenStore())
//                .tokenEnhancer(tokenEnhancer())
//                .userDetailsService(userDetailService)
//                .authenticationManager(authenticationManagerBean)
//                .reuseRefreshTokens(false);
//                //.exceptionTranslator(new PigxWebResponseExceptionTranslator());
//    }
//
//
//
//
//    /**
//     *
//     * todo  token增强，客户端模式不增强。
//     *
//     * @return TokenEnhancer
//     */
//    @Bean
//    public TokenEnhancer tokenEnhancer() {
//        return (accessToken, authentication) -> {
//            final Map<String, Object> additionalInfo = new HashMap<>(8);
//            User pigxUser = (User) authentication.getUserAuthentication().getPrincipal();
//            additionalInfo.put("username", pigxUser.getUsername());
//            //additionalInfo.put("license", SecurityConstants.VOUCHER_LICENSE);
//            log.info("createAccessToken:{}",accessToken.getValue());
//            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
//            return accessToken;
//        };
//    }
//
//
//    /**
//     * 自定义token
//     * @return
//     */
//    @Bean
//    public TokenStore tokenStore() {
//        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
//        tokenStore.setPrefix("zdytoken");
//        tokenStore.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator() {
//            @Override
//            public String extractKey(OAuth2Authentication authentication) {
//                return super.extractKey(authentication);
//            }
//        });
//        return tokenStore;
//    }
//}
