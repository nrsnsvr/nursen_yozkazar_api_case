package com.petstore.data;

import com.github.javafaker.Faker;
import com.petstore.client.requests.PetRequests;
import com.petstore.model.pets.PetCreateRequestModel;
import com.petstore.model.pets.PetCreateResponseModel;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.awaitility.Awaitility;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class PetDataFactory extends PetRequests {

    private final Faker faker;
    public static final Long NOT_FOUND_ID= -1L;

    public PetDataFactory() {
        faker = new Faker();
    }

    private PetCreateRequestModel generatePetBody() {
        PetCreateRequestModel.Category category = PetCreateRequestModel.Category.builder()
                .id(Long.parseLong(faker.code().ean8()))
                .name(faker.animal().name())
                .build();

        return PetCreateRequestModel.builder()
                .id(Long.parseLong(faker.code().ean8()))
                .name(faker.animal().name())
                .category(category)
                .status("available")
                .photoUrls(List.of(faker.internet().url()))
                .tags(List.of(PetCreateRequestModel.Tag.builder()
                        .id(Long.parseLong(faker.code().ean8()))
                        .name(faker.funnyName().name())
                        .build()))
                .build();
    }

    public PetCreateResponseModel createNewPet() {
        var body = generatePetBody();
        return postPet(body)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .as(PetCreateResponseModel.class);
    }

    //awaitility
    public void waitForPetExist(Long petId) {
       Awaitility.await()
               .atMost(20, TimeUnit.SECONDS)
               .pollInterval(250,TimeUnit.MILLISECONDS)
               .until(getPetExists(petId));
    }

    public Callable<Boolean> getPetExists(Long petId) {
        return () -> {
            Response response = getPetById(petId);
            return response.getStatusCode()==200;
        };
    }
}