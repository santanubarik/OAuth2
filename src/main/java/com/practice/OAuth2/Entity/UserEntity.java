package com.practice.OAuth2.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class UserEntity implements Serializable,UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	Long id;
	
	@NotNull
	@Column(name = "first_name")
	String firstName;
	
	@NotNull
	@Column(name = "last_name")
	String lastName;
	
	@NotNull
	@Column(name = "user_name")
	String userName;
	
	@NotNull
	@Column(name = "password")
	String password;
	
	@OneToOne(mappedBy = "userEntity")
	@JoinColumn(name = "role_id")
	RoleEntity role ;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<RoleEntity> roles = new ArrayList<RoleEntity>();
		roles.add(role);
		return roles;
	}


	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
