package com.stackoverflow.question;

import com.stackoverflow.question.entities.Name;
import com.stackoverflow.question.repositories.NameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NameRepositoryTest {

  @Autowired
  private NameRepository nameRepository;

  @Test
  void save_validName_saved() {
    assertEquals(2, nameRepository.count());
    Name name = new Name();
    name.name = "name 5";

    Long generatedId = assertDoesNotThrow(() -> nameRepository.save(name).id);

    assertNotNull(nameRepository.findById(generatedId));
  }
}