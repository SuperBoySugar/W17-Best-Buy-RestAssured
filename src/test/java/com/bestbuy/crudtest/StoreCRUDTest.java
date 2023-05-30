package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBaseClass;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class StoreCRUDTest extends TestBaseClass {
    static String name = "Crawley";
    static String type = "BigBox";
    static String address = "100 Downland Drive";
    static String address2 = "London Road";
    static String city = "Crawley";
    static String state = "Sussex";
    static String zip = "55305";
    static double lat = 44.969696;
    static double lng = -93.445679;
    static String hours= "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;

    // Create new store with - POST
    @Test
    public void test001(){
        StorePojo storesPojo = new StorePojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(storesPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }


    // Extract storeId of newly created store
    @Test
    public void test002() {
        String p1 = "data.findAll{it.name='";
        String p2 = "'}.get(0)";

        HashMap value =
                given()
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + name + p2);
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(value));
//        storeId = (int) value.get("id"); // Store extracted storeId into variable
    }

    // Update store record with PUT
    @Test
    public void test003() {
        String p1 = "data.findAll{it.name='";
        String p2 = "'}.get(0)";

        // Update 'name' and 'address'
        name = name + " (Updated)";
        address = address + " (Updated)";

        StorePojo storesPojo = new StorePojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);


        given()
                .header("Content-Type", "application/json")
                .pathParam("storeID", storeId)
                .body(storesPojo)
                .when()
                .put("/{storeID}")
                .then().log().all().statusCode(200);

        // Assert response with 'id' after extracting by 'name'
        HashMap<String, Object> value =

                given()
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + name + p2);
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(value));
    }

    // Delete record with - DELETE
    @Test
    public void test004(){

        given()
                .pathParam("storeID", storeId)
                .when()
                .delete("/{storeID}")
                .then()
                .statusCode(200);

        given()
                .pathParam("storeID", storeId)
                .when()
                .get("/{storeID}")
                .then()
                .statusCode(404);

    }


}
