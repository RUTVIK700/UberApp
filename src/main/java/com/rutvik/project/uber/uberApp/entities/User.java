package com.rutvik.project.uber.uberApp.entities;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rutvik.project.uber.uberApp.enums.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "app_user", indexes = { @Index(name = "idx_user_email", columnList = "email"), })
@Getter
@Setter
public final class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<Roles> role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return role.stream().map(r->new SimpleGrantedAuthority("ROLE_"+r.name()))
				.collect(Collectors.toSet());
	}

	@Override
	public String getUsername() {
		return email;
	}
}
