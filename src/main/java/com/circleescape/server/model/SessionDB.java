package com.circleescape.server.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SessionDB {

	/* prototype with map and fifo record, switch to h2 eventually */
	private Map<Integer, Game> sessions;
	private LinkedList<Integer> keyHistory;
	public SessionDB() {
		log.info("SessionDB() called: " + this.toString());
		sessions = new HashMap<Integer, Game>();
		keyHistory = new LinkedList<Integer>();
	}
	
	public Optional<Game> getGame(int id) {
		log.info("SessionDB.getGame called: " + this.toString());
		
		Game g = sessions.get(id);
		return g != null ? Optional.of(g)
		                 : Optional.empty();
	}
	
	public Integer put(Game game) {
		log.info("SessionDB.put called: " + this.toString());
		Set<Integer> keys = sessions.keySet();
		Random r = new Random(0);
		int i = r.ints().filter(k->!sessions.containsKey(k)).findFirst().getAsInt();
		sessions.put(i, game);
		keyHistory.add(i);
		while(keyHistory.size() > 100) {
			sessions.remove(keyHistory.removeFirst());
		}
		return i;
	}
}
