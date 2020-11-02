package com.circleescape.server.model;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Game {
	
	private double speedFactor;
	private double radius;
	
	private PolarCoordinates escapee;
	private double guard;
	
	private GuardStrategy guardStrategy = new GreedyGuard();
	
	TurnResult state;
	
	@Getter
	private boolean hasbeeninitialized;
	
	private void logf(String format, Object... args) {
		log.info(String.format(format, args));
	}
	
	//TODO learn how to initialize this bean in constructor
	public void initialize(GameParameters gp) {
		this.speedFactor = gp.getSpeedFactor();
		this.radius = gp.getRadius();
		this.escapee = new PolarCoordinates(0, MathUtils.TAU/4);
		this.guard = MathUtils.TAU*3/4;
		this.state = TurnResult.ONGOING;
		logf("this is game");
		logf(this.toString());
		logf("escapee null? %b",this.escapee==null);
		hasbeeninitialized=true;
	}
	
	public GameParameters getParameters() {
		return new GameParameters(radius, speedFactor);
	}
	
	public GameState getGameState() {
		logf("game state");
		logf("hasbeeninitialized: %b",hasbeeninitialized);
		logf("this is game");
		logf(this.toString());
		logf("escapee null? %b",this.escapee==null);

		Cat cat = new Cat(guard);
		GameState gs = new GameState(speedFactor, radius, escapee, cat, state);

		return gs;
	}
	
	public Positions getPositions() {
		return new Positions(escapee, new PolarCoordinates(radius, guard));
	}
	
	public Result moveEscapee(Diff d) {
		return null;
	}

	public TurnResponse predictGameState(PolarCoordinates newDuck) {
		
		newDuck = newDuck.normalize(radius);
		
		double guard = guardStrategy.updateCat(getParameters(), this.guard, escapee, newDuck);
		
		TurnResult state = duckAtTheEdge(newDuck) ? catAtDuck(guard, newDuck) ? TurnResult.LOOSE
		                                                                      : TurnResult.WIN
		                                          : TurnResult.ONGOING;
		
		return new TurnResponse(newDuck, guard, state);
	}

	
	public TurnResponse updateEscapeePosition(PolarCoordinates newEscapeePosition) {
		TurnResponse tr = predictGameState(newEscapeePosition);
		this.escapee = tr.getDuck();
		this.guard = tr.getCat().getTheta();
		this.state = tr.getState();
		return tr;
	}
	
	private boolean duckAtTheEdge(PolarCoordinates duck) {
		return Math.abs(radius - duck.getR()) < MathUtils.ϵ;
	}
	
	private boolean catAtDuck(double cat, PolarCoordinates duck) {
		return Math.abs(cat - duck.getTheta()) < MathUtils.ϵ;
	}


}
