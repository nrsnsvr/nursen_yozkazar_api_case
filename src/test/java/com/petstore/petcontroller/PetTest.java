package com.petstore.petcontroller;

import com.petstore.BaseTest;
import com.petstore.model.error.ErrorModel;
import com.petstore.model.pets.PetCreateResponseModel;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.petstore.client.constants.MatcherMessage.PET_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;

import static com.petstore.client.customassertions.CustomAssertion.assertResponseTimeLessThan;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

public class PetTest extends BaseTest {

    @Test
    public void PetController_PostPet_ShouldCreatePetSuccessfully() {
        //Given
        var petRequestModel = petDataFactory.createNewPet();
        //When
        Response response = petRequests.postPet(petRequestModel);
        //Then
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var pet = response.as(PetCreateResponseModel.class);
        assertThat(petRequestModel).usingRecursiveComparison().isEqualTo(pet);

    }

    @Test
    public void PetController_PostPet_WhenNullId_ShouldCreatePetSuccessfully() {

        var petRequestModel = petDataFactory.createNewPet();
        petRequestModel.setId(null);

        Response response = petRequests.postPet(petRequestModel);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var pet = response.as(PetCreateResponseModel.class);

        assertNotNull(pet.getId());
        assertThat(petRequestModel).usingRecursiveComparison().ignoringFields("id").isEqualTo(pet);

    }

    @Test
    public void PetController_PostPet_WhenNullName_ShouldCreatePetSuccessfully() {

        var petRequestModel = petDataFactory.createNewPet();
        petRequestModel.setName("");

        Response response = petRequests.postPet(petRequestModel);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var pet = response.as(PetCreateResponseModel.class);

        assertThat(petRequestModel).usingRecursiveComparison().isEqualTo(pet);

    }

    @Test
    public void PetController_PostPet_WhenNullPhotoUrls_ShouldCreatePetSuccessfully() {

        var petRequestModel = petDataFactory.createNewPet();
        petRequestModel.setPhotoUrls(null);

        Response response = petRequests.postPet(petRequestModel);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var pet = response.as(PetCreateResponseModel.class);

        assertThat(petRequestModel).usingRecursiveComparison().isEqualTo(pet);

    }

    @Test
    public void PetController_PostPet_WhenNullStatus_ShouldCreatePetSuccessfully() {

        var petRequestModel = petDataFactory.createNewPet();
        petRequestModel.setStatus(null);

        Response response = petRequests.postPet(petRequestModel);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var pet = response.as(PetCreateResponseModel.class);

        assertThat(petRequestModel).usingRecursiveComparison().isEqualTo(pet);

    }

    @Test
    public void PetController_PostPet_WhenNullCategory_ShouldCreatePetSuccessfully() {

        var petRequestModel = petDataFactory.createNewPet();
        petRequestModel.setCategory(null);

        Response response = petRequests.postPet(petRequestModel);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var pet = response.as(PetCreateResponseModel.class);

        assertThat(petRequestModel).usingRecursiveComparison().isEqualTo(pet);

    }

    @Test
    public void PetController_PostPet_WhenNullIdInCategory_ShouldCreatePetSuccessfully() {

        var petRequestModel = petDataFactory.createNewPet();
        petRequestModel.getCategory().setId(null);

        Response response = petRequests.postPet(petRequestModel);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var pet = response.as(PetCreateResponseModel.class);

        assertThat(petRequestModel).usingRecursiveComparison().ignoringFields("category.id").isEqualTo(pet);

    }

    @Test
    public void PetController_PostPet_WhenNullNameInCategory_ShouldCreatePetSuccessfully() {

        var petRequestModel = petDataFactory.createNewPet();
        petRequestModel.getCategory().setName("");

        Response response = petRequests.postPet(petRequestModel);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var pet = response.as(PetCreateResponseModel.class);

        assertThat(petRequestModel).usingRecursiveComparison().isEqualTo(pet);

    }

    @Test
    public void PetController_PostPet_WhenNullTags_ShouldCreatePetSuccessfully() {

        var petRequestModel = petDataFactory.createNewPet();
        petRequestModel.setTags(null);

        Response response = petRequests.postPet(petRequestModel);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var pet = response.as(PetCreateResponseModel.class);

        assertThat(petRequestModel).usingRecursiveComparison().isEqualTo(pet);

    }

    @Test
    public void PetController_PostPet_WhenNullIdInTags_ShouldCreatePetSuccessfully() {

        var petRequestModel = petDataFactory.createNewPet();
        petRequestModel.getTags().get(0).setId(null);

        Response response = petRequests.postPet(petRequestModel);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var pet = response.as(PetCreateResponseModel.class);

        assertThat(petRequestModel).usingRecursiveComparison().ignoringFields("tags.id").isEqualTo(pet);

    }

    @Test
    public void PetController_PostPet_WhenNullNameInTags_ShouldCreatePetSuccessfully() {

        var petRequestModel = petDataFactory.createNewPet();
        petRequestModel.getTags().get(0).setName("");

        Response response = petRequests.postPet(petRequestModel);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var pet = response.as(PetCreateResponseModel.class);

        assertThat(petRequestModel).usingRecursiveComparison().isEqualTo(pet);

    }

    @Test
    public void PetController_PutPet_WhenUpdatedName_ShouldUpdatePetSuccessfully() {
        var petRequestModel = petDataFactory.createNewPet();
        petRequests.postPet(petRequestModel);

        petRequestModel.setName("Updated");
        Response response = petRequests.putPet(petRequestModel);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var updatedPet = response.as(PetCreateResponseModel.class);
        assertThat(petRequestModel).usingRecursiveComparison().isEqualTo(updatedPet);
    }

    @Test
    public void PetController_PutPet_WhenUpdatedId_ShouldUpdatePetSuccessfully() {
        var petRequestModel = petDataFactory.createNewPet();
        petRequests.postPet(petRequestModel);

        petRequestModel.setId(1000L);
        Response response = petRequests.putPet(petRequestModel);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var updatedPet = response.as(PetCreateResponseModel.class);
        assertThat(petRequestModel).usingRecursiveComparison().isEqualTo(updatedPet);
    }

    @Test
    public void PetController_PutPet_WhenUpdatedPhotoUrls_ShouldUpdatePetSuccessfully() {
        var petRequestModel = petDataFactory.createNewPet();
        petRequests.postPet(petRequestModel);

        petRequestModel.setPhotoUrls(List.of("Updated"));
        Response response = petRequests.putPet(petRequestModel);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var updatedPet = response.as(PetCreateResponseModel.class);
        assertThat(petRequestModel).usingRecursiveComparison().isEqualTo(updatedPet);
    }

    @Test
    public void PetController_PutPet_WhenUpdatedStatus_ShouldUpdatePetSuccessfully() {
        var petRequestModel = petDataFactory.createNewPet();
        petRequests.postPet(petRequestModel);

        petRequestModel.setStatus("Pending");
        Response response = petRequests.putPet(petRequestModel);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        var updatedPet = response.as(PetCreateResponseModel.class);
        assertThat(petRequestModel).usingRecursiveComparison().isEqualTo(updatedPet);
    }

    @Test
    public void PetController_PutPet_WhenUpdatedCategory_ShouldUpdatePetSuccessfully() {
        var petRequestModel = petDataFactory.createNewPet();
        petRequests.postPet(petRequestModel);

        Long id = 1000L;
        var name = "Updated";

        var updatedPet = petRequestModel;
        petRequestModel.getCategory().setId(id);
        petRequestModel.getCategory().setName(name);

        var response = petRequests.putPet(updatedPet);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        var pet = response.as(PetCreateResponseModel.class);
        assertThat(pet.getCategory().getId()).isEqualTo(id);
        assertThat(pet.getCategory().getName()).isEqualTo(name);
    }

    @Test
    public void PetController_PutPet_WhenUpdatedTags_ShouldUpdatePetSuccessfully() {
        var petRequestModel = petDataFactory.createNewPet();
        petRequests.postPet(petRequestModel);

        Long id = 1000L;
        var name = "Updated";

        var updatedPet = petRequestModel;
        petRequestModel.getTags().get(0).setId(id);
        petRequestModel.getTags().get(0).setName(name);

        var response = petRequests.putPet(updatedPet);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        var pet = response.as(PetCreateResponseModel.class);
        assertThat(pet.getTags().get(0).getId()).isEqualTo(id);
        assertThat(pet.getTags().get(0).getName()).isEqualTo(name);
    }

    @Test
    public void PetController_GetPetById_ShouldGetPetSuccessfully() {
        //Precondition
        var newPet = petDataFactory.createNewPet();
        var petId = newPet.getId();
        //When
        petDataFactory.waitForPetExist(petId); //async operationlar için dinamik wait eklendi.
        //Async wait olmasına rağmen bir kaç kez deneme yapıldığında bazı durumlarda test patlıyor. Api'de bir bug var.
        var response = petRequests.getPetById(petId);
        //Then
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        var pet = response.as(PetCreateResponseModel.class);
        assertThat(newPet).usingRecursiveComparison().isEqualTo(pet); //Yorum satırındaki alan kontrolleri ile aynı işi yapıyor.
//        assertEquals(newPet.getId(), pet.getId());
//        assertThat(newPet.getCategory()).usingRecursiveComparison().isEqualTo(pet.getCategory());
//        assertEquals(newPet.getName(), pet.getName());
//        assertEquals(newPet.getTags().get(0).getId(), pet.getTags().get(0).getId());
//        assertEquals(newPet.getTags().get(0).getName(), pet.getTags().get(0).getName());
//        assertEquals(newPet.getStatus(), pet.getStatus());

    }

    @Test
    public void PetController_GetPetById_WhenInvalidPetId_ShouldReturnNotFound() {

        long invalidPetId = -1;

        var response = petRequests.getPetById(invalidPetId);

        assertThat(response.getStatusCode()).isIn(HttpStatus.SC_BAD_REQUEST, HttpStatus.SC_NOT_FOUND);

        ErrorModel errorModel = response.as(ErrorModel.class);

        assertEquals(errorModel.getMessage(), PET_NOT_FOUND);
    }

    @Test
    public void PetController_DeletePet_ShouldDeletePetSuccessfully() {

        var petRequestModel = petDataFactory.createNewPet();
        petRequests.postPet(petRequestModel);
        Long petId = petRequestModel.getId();

        Response deleteResponse = petRequests.deletePetById(petId);

        assertEquals(HttpStatus.SC_OK, deleteResponse.getStatusCode());
        assertResponseTimeLessThan(deleteResponse, 1000);

        Response getResponse = petRequests.getPetById(petId);
        assertThat(getResponse.getStatusCode()).isIn(HttpStatus.SC_NOT_FOUND, HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void PetController_DeletePet_WhenInvalidId_ShouldReturnBadRequest() {
        long invalidPetId = -1;

        Response response = petRequests.deletePetById(invalidPetId);

        assertThat(response.getStatusCode()).isIn(HttpStatus.SC_BAD_REQUEST, HttpStatus.SC_NOT_FOUND);

    }

    @Test
    public void PetController_GetPetsByStatus_WhenAvailableStatus_ShouldReturnPets() {
        Map<String, List<?>> params = new HashMap<>();
        var status = "available";
        params.put("status", List.of(status));

        Response response = petRequests.getPetByFindByStatus(params);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        List<PetCreateResponseModel> pets = Arrays.asList(response.getBody().as(PetCreateResponseModel[].class));
        assertThat(pets).isNotEmpty();
        pets.forEach(pet -> assertEquals(status, pet.getStatus()));
    }

    @Test
    public void PetController_GetPetsByStatus_WhenPendingStatus_ShouldReturnPets() {
        Map<String, List<?>> params = new HashMap<>();
        var status = "pending";
        params.put("status", List.of(status));

        Response response = petRequests.getPetByFindByStatus(params);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        List<PetCreateResponseModel> pets = Arrays.asList(response.getBody().as(PetCreateResponseModel[].class));
        assertThat(pets).isNotEmpty();
        pets.forEach(pet -> assertEquals(status, pet.getStatus()));
    }

    @Test
    public void PetController_GetPetsByStatus_WhenSoldStatus_ShouldReturnPets() {
        Map<String, List<?>> params = new HashMap<>();
        var status = "sold";
        params.put("status", List.of(status));

        Response response = petRequests.getPetByFindByStatus(params);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        List<PetCreateResponseModel> pets = Arrays.asList(response.getBody().as(PetCreateResponseModel[].class));
        assertThat(pets).isNotEmpty();
        pets.forEach(pet -> assertEquals(status, pet.getStatus()));
    }

    @Test
    public void PetController_GetPetsByStatus_WhenMultipleStatuses_ShouldReturnMatchingPets() {

        List<String> statuses = List.of("available", "pending", "sold");
        Map<String, List<?>> params = new HashMap<>();
        params.put("status", statuses);

        Response response = petRequests.getPetByFindByStatus(params);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertResponseTimeLessThan(response, 1000);

        List<PetCreateResponseModel> pets = Arrays.asList(response.getBody().as(PetCreateResponseModel[].class));
        assertThat(pets).isNotEmpty();

        pets.forEach(pet ->
                assertThat(statuses).contains(pet.getStatus())
        );
    }


}
