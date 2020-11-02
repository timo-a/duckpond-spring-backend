package com.circleescape.server.model;

import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair {

	@NotNull
	private int sessionID;
	@NotNull
	private GameState gamestate;
}
