package com.circleescape.server.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.circleescape.server.CircleEscapeServerApplication;
import com.circleescape.server.scoreboard.data.ScoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest(classes = {CircleEscapeServerApplication.class})
@AutoConfigureMockMvc
public class MockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ScoreRepository scoreRepository;

    @Test
    void trigger_validBody_savesName() throws Exception {
        assertEquals(2, scoreRepository.count());

        mockMvc.perform(
                post("/scoreboard/4/postName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"name\":\"Jacko\""))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        assertEquals(3, scoreRepository.count());
    }
}
