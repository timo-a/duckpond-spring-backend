package com.circleescape.server.persistence;

import com.circleescape.server.scoreboard.data.Score;
import com.circleescape.server.scoreboard.data.ScoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NameRepositoryTest {

  @Autowired
  private ScoreRepository scoreRepository;

  @Test
  void save_validName_saved() {
    assertEquals(2, scoreRepository.count());
    Score score = new Score(4L, "hugo", 6.7f, LocalDateTime.MAX);

    Long generatedId = assertDoesNotThrow(() -> scoreRepository.save(score).getId());

    assertNotNull(scoreRepository.findById(generatedId));
  }
}