package com.opinionet.opinionetservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import com.opinionet.opinionetservice.web.GameController;
import com.opinionet.opinionetservice.web.GenreController;
import com.opinionet.opinionetservice.web.PlatformController;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmokeTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GameController gameController;

    @Autowired
    private GenreController genreController;

    @Autowired
    private PlatformController platformController;

    @Test
    public void indexPageLoads() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
        .contains("All Games");
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
    //TODO: UPDATE!
}
