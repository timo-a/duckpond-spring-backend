package com.circleescape.server.model

import org.slf4j.LoggerFactory
import java.util.*

class SessionDB {

    private val log = LoggerFactory.getLogger(this.javaClass)

    /* prototype with map and fifo record, switch to h2 eventually */
    private val sessions: MutableMap<Int, Game>
    private val keyHistory: LinkedList<Int>

    init {
        log.info("SessionDB() called: $this")
        sessions = HashMap()
        keyHistory = LinkedList()
    }

    fun getGame(id: Int): Optional<Game> {
        log.info("SessionDB.getGame called: $this")
        val g = sessions[id]
        return if (g != null) Optional.of(g) else Optional.empty()
    }

    fun put(game: Game): Int {
        log.info("SessionDB.put called: $this")
        val keys: Set<Int> = sessions.keys
        val r = Random(0)
        val i = r.ints().filter { k: Int -> !sessions.containsKey(k) }.findFirst().asInt
        sessions[i] = game
        keyHistory.add(i)
        while (keyHistory.size > 100) {
            sessions.remove(keyHistory.removeFirst())
        }
        return i
    }
}