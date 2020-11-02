package com.circleescape.server.model;

import javax.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Decorate Objects with sessionID")
public class PairGeneric<T> {
	
	@NotNull
	@Parameter(description = "explicit session id", required = true)
	private Integer sessionID;
	
	@NotNull
	@Parameter(description = "my first attribute", required = true)
	private T t;
}
