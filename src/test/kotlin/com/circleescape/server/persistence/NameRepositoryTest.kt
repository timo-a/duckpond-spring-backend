package com.circleescape.server.persistence

import com.circleescape.server.scoreboard.data.Score
import com.circleescape.server.scoreboard.data.ScoreRepository
import org.amshove.kluent.`should be true`
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import java.time.LocalDateTime
import java.util.stream.StreamSupport

@SpringBootTest
class NameRepositoryTest {

  @Autowired
  private val scoreRepository: ScoreRepository? = null

  @BeforeEach
  fun deleteJacko() {
    //this is a hack todo make custom sql files for tests
    StreamSupport.stream(scoreRepository!!.findAll().spliterator(), false)
            .map { it!!.id!! }.filter{it > 2}
            .forEach(scoreRepository::deleteById)
  }

  @Test
  fun save_validName_saved() {
    scoreRepository!!.findAll().forEach(System.out::println)
    scoreRepository.count().`should be` (2)
    val score = Score(4L, "hugo", 6.7, LocalDateTime.MAX, null)

    val generatedId: Long = assertDoesNotThrow { scoreRepository.save(score).id!! }

    scoreRepository.findById(generatedId).isPresent.`should be true`()
  }
}