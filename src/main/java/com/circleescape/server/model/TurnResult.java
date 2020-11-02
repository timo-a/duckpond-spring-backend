package com.circleescape.server.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum TurnResult {
	WIN, LOOSE, ONGOING
	
}