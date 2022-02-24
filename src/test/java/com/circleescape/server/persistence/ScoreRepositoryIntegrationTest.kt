package com.circleescape.server.persistence

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.beans.factory.annotation.Autowired
import com.circleescape.server.scoreboard.data.ScoreRepository
import io.restassured.RestAssured
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ScoreRepositoryIntegrationTest {
    @Autowired
    private val nameRepository: ScoreRepository? = null

    @Value("\${local.server.port}")
    private val serverPort = 0
    @Test
    fun triggerTest() {

        //assert data is even loaded
        Assertions.assertEquals(2, nameRepository!!.count())
        println("port: $serverPort")
        RestAssured
            .given()
            .baseUri("http://localhost")
            .port(serverPort)
            .basePath("scoreboard")
            .body("Jack")
            .`when`()
            .post("/4/postName")
            .then()
            .statusCode(HttpStatus.OK.value())

        //assert new name is added
        Assertions.assertEquals(3, nameRepository.count())
        Assertions.assertEquals("Jack", nameRepository.findById(3L).get().name)
    }
}