//package com.example.demo.service.impl;
//
//import cn.hutool.core.util.ArrayUtil;
//import cn.hutool.core.util.StrUtil;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.example.demo.entity.SysRole;
//import com.example.demo.entity.SysUser;
//import com.example.demo.entity.UserInfo;
//import com.example.demo.mapper.SysUserMapper;
//import com.example.demo.secutiry.SecurityConst;
//import com.example.demo.service.SysRoleService;
//import com.example.demo.service.SysUserService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * @author 王杰
// * @date 2018/10/31
// */
//@Slf4j
//@Service
//@AllArgsConstructor
//public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
//	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
//
//	@Autowired
//	private SysRoleService sysRoleService;
//
//	@Override
//	public UserDetails getUserDetails(String username) {
//		UserInfo info = findUserInfo(username);
//		Set<String> dbAuthsSet = new HashSet<>();
//		if (ArrayUtil.isNotEmpty(info.getRoles())) {
//			// 获取角色
//			Arrays.stream(info.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConst.ROLE + roleId));
//			// 获取资源
//			//dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));
//		}
//		Collection<? extends GrantedAuthority> authorities
//				= AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
//		SysUser user = info.getSysUser();
//		boolean enabled = StrUtil.equals(user.getLockFlag(), String.valueOf(0));
//		// 构造security用户
////		return new User(user.getUserId(), user.getDeptId(), user.getTenantId(), user.getUsername(), SecurityConst.BCRYPT + user.getPassword(), enabled,
////				true, true, "9".equals(user.getLockFlag()), authorities);
//		return new User(user.getUsername(), user.getPassword(), enabled, true, true, !"9".equals(user.getLockFlag()), authorities);
//	}
//
//
//
//	/**
//	 * 通过查用户的全部信息
//	 *
//	 * @param username 用户名
//	 * @return
//	 */
//	public UserInfo findUserInfo(String username) {
//		SysUser sysUser = this.baseMapper.selectOne(Wrappers.<SysUser>query()
//				.lambda().eq(SysUser::getUsername, username));
//
//		UserInfo userInfo = new UserInfo();
//		userInfo.setSysUser(sysUser);
//		//设置角色列表  （ID）
//		List<Integer> roleIds = sysRoleService.findRolesByUserId(sysUser.getUserId())
//				.stream()
//				.map(SysRole::getRoleId)
//				.collect(Collectors.toList());
//		userInfo.setRoles(ArrayUtil.toArray(roleIds, Integer.class));
//
////		//设置权限列表（menu.permission）
////		Set<String> permissions = new HashSet<>();
////		roleIds.forEach(roleId -> {
////			List<String> permissionList = sysMenuService.findMenuByRoleId(roleId)
////					.stream()
////					.filter(menuVo -> StringUtils.isNotEmpty(menuVo.getPermission()))
////					.map(MenuVO::getPermission)
////					.collect(Collectors.toList());
////			permissions.addAll(permissionList);
////		});
////		userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
//		return userInfo;
//	}
//
//}
