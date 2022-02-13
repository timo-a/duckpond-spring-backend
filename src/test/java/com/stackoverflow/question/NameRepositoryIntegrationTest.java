package com.stackoverflow.question;

import com.stackoverflow.question.repositories.NameRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NameRepositoryIntegrationTest {

    @Autowired
    private NameRepository nameRepository;

    @Test
    public void triggerTest() {

        //assert data is even loaded
        assertEquals(2, nameRepository.count());

        RestAssured
                .given()
                .baseUri("http://localhost")
                .port(8080)

                .when()
                .post("triggerError")

                .then()
                .statusCode(HttpStatus.OK.value());

        //assert new name is added
        assertEquals(3, nameRepository.count());
    }

}
