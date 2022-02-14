package com.stackoverflow.question.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.stackoverflow.question.MyApplication;
import com.stackoverflow.question.repositories.NameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest(classes = {MyApplication.class})
@AutoConfigureMockMvc
public class MyControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private NameRepository nameRepository;

  @Test
  void trigger_validBody_savesName() throws Exception {
    assertEquals(2, nameRepository.count());

    mockMvc.perform(post("/triggerError")).andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());

    assertEquals(3, nameRepository.count());
  }
}
