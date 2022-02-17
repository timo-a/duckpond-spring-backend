package com.circleescape.server.api.game.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum TurnResult {
	WIN, LOOSE, ONGOING
	
}