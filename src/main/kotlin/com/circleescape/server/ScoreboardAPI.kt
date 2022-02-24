package com.circleescape.server

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime
import com.circleescape.server.scoreboard.data.Score
import com.circleescape.server.scoreboard.data.ScoreRepository
import org.springframework.web.bind.annotation.PathVariable
import java.lang.Void
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@CrossOrigin
@RestController
@Service
class ScoreboardAPI @Autowired constructor(private val scoreRepository: ScoreRepository) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @RequestMapping("/")
    fun showRules(): String {
        return "Hi there"
    }

    @RequestMapping("/rules")
    fun showRules2(): String {
        return "Hi there"
    }

    @PostMapping("/scoreboard/{gameId}/postName")
    fun postNameOnScoreboard(
        @PathVariable gameId: Long,
        @RequestBody @org.springframework.web.bind.annotation.RequestBody name: String
    ): ResponseEntity<Void> {
        val gameFinished = true //TODO
        val timestamp = LocalDateTime.now()
        val scoreBE = Score(gameId, name, 42.0, timestamp)
        scoreRepository.save(scoreBE)
        return ResponseEntity.ok().build()
    }
}