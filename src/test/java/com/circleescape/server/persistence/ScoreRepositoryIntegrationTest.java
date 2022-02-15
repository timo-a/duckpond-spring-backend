package com.circleescape.server.persistence;

import com.circleescape.server.scoreboard.data.ScoreRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ScoreRepositoryIntegrationTest {
    @Autowired
    private ScoreRepository nameRepository;

    @Value("${local.server.port}")
    private int serverPort;

    @Test
    public void triggerTest() {

        //assert data is even loaded
        assertEquals(2, nameRepository.count());
        System.out.println("port: "+serverPort);

        RestAssured
                .given()
                .baseUri("http://localhost")
                .port(serverPort)
                .basePath("game")
                .body("Jack")

                .when()
                .post("/4/postName")

                .then()
                .statusCode(HttpStatus.OK.value());

        //assert new name is added
        assertEquals(3, nameRepository.count());
        assertEquals("Jack", nameRepository.findById(3L).get().getName());
    }
}
