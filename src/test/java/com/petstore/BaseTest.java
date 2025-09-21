package com.petstore;

import com.petstore.client.requests.PetRequests;
import com.petstore.config.Configuration;
import com.petstore.config.ConfigurationManager;
import com.petstore.data.PetDataFactory;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {

    protected static Configuration configuration;
    protected PetDataFactory petDataFactory;
    protected PetRequests petRequests;

    private static void determineLog() {
        if (configuration.logAll()) {
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        } else if (configuration.logAllure()) {
            RestAssured.filters(new AllureRestAssured());
        } else {
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        }
    }

    @BeforeSuite
    public void setupSuite() {
        configuration = ConfigurationManager.getConfiguration();
        baseURI = configuration.petstoreApiUri();

        determineLog();

        System.out.println(baseURI);
    }

    @BeforeClass
    public void setupClass() {
        petDataFactory = new PetDataFactory();
        petRequests = new PetRequests();
    }

}
