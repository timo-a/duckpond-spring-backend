package com.circleescape.server.persistence

import org.springframework.boot.test.context.SpringBootTest
import com.circleescape.server.CircleEscapeServerApplication
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import com.circleescape.server.scoreboard.data.ScoreRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import kotlin.Throws
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.lang.Exception

@SpringBootTest(classes = [CircleEscapeServerApplication::class])
@AutoConfigureMockMvc
class MockMvcTest {

    @Autowired
    private val mockMvc: MockMvc? = null

    @Autowired
    private val scoreRepository: ScoreRepository? = null

    @Test
    @Throws(Exception::class)
    fun `trigger validBody savesName`() {
        Assertions.assertEquals(2, scoreRepository!!.count())
        mockMvc!!.perform(
            MockMvcRequestBuilders.post("/scoreboard/4/postName")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"name\":\"Jacko\"")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
        Assertions.assertEquals(3, scoreRepository.count())
    }
}