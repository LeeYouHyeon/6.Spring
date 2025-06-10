package com.koreait.www.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.koreait.www.domain.UserVO;

import lombok.Getter;

public class AuthUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	private UserVO uvo;

//	public AuthUser(String username, String password, boolean enabled, boolean accountNonExpired,
//			boolean credentialsNonExpired, boolean accountNonLocked,
//			Collection<? extends GrantedAuthority> authorities) {
//		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//		// TODO Auto-generated constructor stub
//	}

	public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	
	public AuthUser(UserVO uvo) {
		super(uvo.getEmail(), uvo.getPwd(),
				uvo.getAuthList()
					.stream()
					.map(authVO -> new SimpleGrantedAuthority(authVO.getAuth()))
					.collect(Collectors.toList()));
		this.uvo = uvo;
	}
}
