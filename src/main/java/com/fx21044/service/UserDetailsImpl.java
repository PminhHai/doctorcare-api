package com.fx21044.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fx21044.entity.User;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String name;
	
	private String email;
	
	@JsonIgnore
	private String password;
	
	private String role;
	
	private int isNotBlocked;
	
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(int id, String name, String email, String password, int isNotBlocked,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.isNotBlocked = isNotBlocked;
		this.role = new ArrayList<>(authorities).get(0).getAuthority();
		this.authorities = authorities;
	}
	
	
	public static UserDetailsImpl build(User user) {
		
		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().trim()));
//				Arrays.stream(user.getRole().split(","))
//				.map(SimpleGrantedAuthority::new)
//				.collect(Collectors.toList());
		
		return new UserDetailsImpl(user.getId(), user.getName(), user.getEmail(), user.getPassword(),user.getIsNotBlocked(), authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public int getId() {
		
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRole() {
		return role;
	}

	public int getIsNotBlocked() {
		return isNotBlocked;
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
		if(this.isNotBlocked == 0) {
			
			return false;
			
		}
		
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || getClass() != obj.getClass())
		{
			return false;
		}
		
		UserDetailsImpl user = (UserDetailsImpl) obj;
			
		return Objects.equals(id, user.id);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	
}
