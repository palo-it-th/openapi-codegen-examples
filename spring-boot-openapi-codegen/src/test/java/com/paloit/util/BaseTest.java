package com.paloit.util;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.config;
import static io.restassured.config.LogConfig.logConfig;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected static final String PATH_PREFIX = "/api/v3";

    @LocalServerPort
    private int port;

    @BeforeAll
    public void beforeClass() {
        setBaseURI("http://localhost:" + port);
        config =
                config()
                        .logConfig(
                                logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)
                        );
    }

    private static void setBaseURI (String baseURI){
        RestAssured.baseURI = baseURI;
    }
}
