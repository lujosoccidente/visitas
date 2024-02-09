package com.lujos_occdente.security;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class Principal extends User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6769338313142063010L;
	private String fullName;
	
	public Principal(String username, String password, Collection<? extends GrantedAuthority> authorities, String fullName) {
		super(username, password, authorities);
		this.fullName = fullName;
	}
	
	
	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(fullName);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Principal other = (Principal) obj;
		return Objects.equals(fullName, other.fullName);
	}
}
