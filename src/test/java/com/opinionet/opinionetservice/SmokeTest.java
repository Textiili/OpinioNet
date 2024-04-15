package com.opinionet.opinionetservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import com.opinionet.opinionetservice.web.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmokeTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RestServiceController restServiceController;

    @Autowired
    private UserController userController;

    @Autowired
    private CustomErrorController customErrorController;

    @Autowired
    private GameController gameController;

    @Autowired
    private GenreController genreController;

    @Autowired
    private PlatformController platformController;

    @Test
    public void indexPageLoads() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
        .contains("Login");
    }

    @Test
    public void LoginPageLoads() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/login", String.class))
        .contains("Login");
    }

    @Test
    public void registerPageLoads() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/register", String.class))
        .contains("Register");
    }

    @Test
    public void gameControllerNotNull() {
        assertThat(gameController).isNotNull();
    }

    @Test
    public void genreControllerNotNull() {
        assertThat(genreController).isNotNull();
    }

    @Test
    public void platformControllerNotNull() {
        assertThat(platformController).isNotNull();
    }

    @Test
    public void userControllerNotNull() {
        assertThat(userController).isNotNull();
    }

    @Test
    public void restServiceControllerNotNull() {
        assertThat(restServiceController).isNotNull();
    }

    @Test
    public void customErrorControllerNotNull() {
        assertThat(customErrorController).isNotNull();
    }
}
