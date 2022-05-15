//package com.example.demo.secutiry.config;
//
//import com.example.demo.secutiry.MyUser;
//import com.example.demo.service.SysUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailService implements UserDetailsService {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private SysUserService sysUserService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return sysUserService.getUserDetails(username);
//    }
//
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        MyUser user = new MyUser();
////        user.setUserName(username);
////        user.setPassword(this.passwordEncoder.encode("123456"));
////        return new User(username, user.getPassword(), user.isEnabled(),
////                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
////                user.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
////    }
//}