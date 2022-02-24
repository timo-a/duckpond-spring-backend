package com.circleescape.server.scoreboard.data

import org.springframework.data.repository.CrudRepository

interface ScoreRepository : CrudRepository<Score, Long>