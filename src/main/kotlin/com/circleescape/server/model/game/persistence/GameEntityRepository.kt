package com.circleescape.server.model.game.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface GameEntityRepository : JpaRepository<GameEntity, Long>