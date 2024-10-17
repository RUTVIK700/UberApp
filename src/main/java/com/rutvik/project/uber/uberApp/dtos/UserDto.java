package com.rutvik.project.uber.uberApp.dtos;

import java.util.Set;

import com.rutvik.project.uber.uberApp.enums.Roles;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	private String name;
	private String email;
	private Set<Roles> role;
}
