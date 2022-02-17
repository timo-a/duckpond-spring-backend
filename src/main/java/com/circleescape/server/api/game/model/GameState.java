package com.circleescape.server.api.game.model;

import javax.validation.constraints.NotNull;

public class GameState {

    @NotNull
    private double speedFactor;
    @NotNull
    private double radius;

    @NotNull
    private Duck duck;

    @NotNull
    private double catAngle;

    @NotNull
    private TurnResult state;

}
