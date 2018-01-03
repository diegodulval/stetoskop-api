package com.dulval.stetoskop.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dulval.stetoskop.domain.enums.Role;
import lombok.Data;

@Data
public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String password;
        private Integer role;
        
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {
	}
	
	public UserSS(Integer id, String email, String password, Integer role) {
		super();
		this.id = id;
		this.email = email;
                this.role = role;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
