package com.circleescape.server.persistence;

import com.circleescape.server.scoreboard.data.Score;
import com.circleescape.server.scoreboard.data.ScoreRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NameRepositoryTest {

  @Autowired
  private ScoreRepository scoreRepository;

  @BeforeEach
  public void deleteJacko() {
    //this is a hack todo make custom sql files for tests
    StreamSupport.stream(scoreRepository.findAll().spliterator(), false)
            .map(Score::getId).filter( id -> id > 2)
            .forEach(scoreRepository::deleteById);
  }

  @Test
  void save_validName_saved() {
    scoreRepository.findAll().forEach(s -> {System.out.println(s);});
    assertEquals(2, scoreRepository.count());
    Score score = new Score(4L, "hugo", 6.7f, LocalDateTime.MAX, null);

    Long generatedId = assertDoesNotThrow(() -> scoreRepository.save(score).getId());

    assertNotNull(scoreRepository.findById(generatedId));
  }
}