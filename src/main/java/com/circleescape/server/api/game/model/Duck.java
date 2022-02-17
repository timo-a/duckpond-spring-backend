package com.circleescape.server.api.game.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Duck {

    @NotNull
    private double radius;

    @NotNull
    private double angle;

}
