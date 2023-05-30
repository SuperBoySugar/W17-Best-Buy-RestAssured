package com.bestbuy.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBaseClass {

    @BeforeClass
    public static void initialize() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";

    }


}
