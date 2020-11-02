package com.circleescape.server.model;

import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Positions {
	

	@NotNull
	private PolarCoordinates escapee;

	@NotNull
	private PolarCoordinates guard;
	

}
