package com.circleescape.server.model;

import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Cat {

	@Getter
	@NotNull
	private double theta;
	
}
