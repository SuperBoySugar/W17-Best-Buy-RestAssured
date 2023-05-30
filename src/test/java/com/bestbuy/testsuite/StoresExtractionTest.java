package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void initialize() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    // 1. Extract the limit
    @Test
    public void test001(){
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    // 2. Extract the total
    @Test
    public void test002(){
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    // 3. Extract the name of 5th store
    @Test
    public void test003(){
        String storeName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of 5th store is : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4. Extract the names of all the store
    @Test
    public void test004(){
        List<String> storeNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Names of all stores : " + storeNames);
        System.out.println("------------------End of Test---------------------------");
    }

    // 5. Extract the storeId of all the store
    @Test
    public void test005(){
        List<Integer> storeIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("StoreId of all stores : " +storeIds);
        System.out.println("------------------End of Test---------------------------");
    }

    // 6. Print the size of the data list
    @Test
    public void test006(){
        List<Integer> dataSize = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Size of data list is : " + dataSize.size());
        System.out.println("------------------End of Test---------------------------");
    }

    // 7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007(){
        List<String> value = response.extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the value of store name St Cloud : " + value);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8. Get the address of the store where store name = Rochester
    @Test
    public void test008(){
        String address = response.extract().path("data[8].address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Address of the store Rochester : " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    // 9. Get all the services of 8th store
    @Test
    public void test009(){
        List<String> services = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All services of 8th Store : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010(){
        List<LinkedHashMap> services = response.extract().path("data.findAll{it.name='Windows Store'}.services");
        String jsonPath = "$.*.services[?(@.name=='Windows Store')].storeservices"; // JSON Path for locating storeservices where service name is Windows Store
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Storeservices of the store where service name Windows Store : " + services);
        System.out.println("------------------End of Test---------------------------");
    }


    // 11. Get all the storeId of all the store
    @Test
    public void test011(){
        List <String> storeId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("StoreId's of all stores : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    // 12. Get id of all the store
    @Test
    public void test012(){
        List<String> id = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Id's of all Stores : " + id);
        System.out.println("------------------End of Test---------------------------");
    }

    // 13. Find the store names Where state = ND
    @Test
    public void test013(){
        List<String> storeName = response.extract().path("data.findAll{it.state=='MN'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store name for state ND is : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014(){
        List<String> services = response.extract().path("data[8].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of services for store 'Rochester' : " + services.size());
        System.out.println("------------------End of Test---------------------------");
    }

    // 15. Find the createdAt for all services whose name = Windows Store
    @Test
    public void test015(){
        List<List<LinkedHashMap>> createdAt = response.extract().path("data.findAll{it.name!=''}");
        String jsonPath = "$.*.services[?(@.name=='Windows Store')].createdAt"; // JSON Path for locating services where name is Windows Store
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All createdAt for all services whose name is 'Windows Store' : " + createdAt);
        System.out.println("All createdAt for all services whose name is 'Windows Store' : " + createdAt.size());
        System.out.println("------------------End of Test---------------------------");
    }

    // 16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016(){
        List<String> services = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of all services for store name 'Fargo' : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    // 17. Find the zip of all the store
    @Test
    public void test017(){
        List<String> zip = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Zip of all stores : " + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    // 18. Find the zip of store name = Roseville
    @Test
    public void test018(){
        String zip =response.extract().path("data[8].zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Zip of store 'Roseville'' : " + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    // 19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019(){
        List<LinkedHashMap> storeServices = response.extract().path("data.findAll{it.name!=''}");
        String jsonPath = "$.*.services[?(@.name=='Magnolia Home Theater')].storeservices"; // JSON Path for locating storeservices where service name is Magnolia Home Theater
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Storeservices details of the service name = Magnolia Home Theater : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    // 20. Find the lat of all the stores
    @Test
    public void test020(){
        List<Integer> lat = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("lat of all stores : " + lat);
        System.out.println("------------------End of Test---------------------------");
    }

}
