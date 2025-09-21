package com.petstore.client.requests;

import com.petstore.client.constants.Endpoints;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static com.petstore.specs.RequestSpecificationHelper.*;

public class PetRequests extends GenericRequests{

    public Response getPetById(Long id) {
        return get(
                getJsonRequestSpecificationWithPathParam("petId", id), Endpoints.PET_PET_ID);
    }

    public Response getPetByFindByStatus(Map<String, List<?>> multiParams) {

        return get(
                getJsonRequestSpecificationWithParams(multiParams), Endpoints.PET_FIND_BY_STATUS);
    }

    public Response postPet(Object body) {
        return post(
                getDefaultJsonRequestSpecification(), Endpoints.PET, body);
    }

    public Response putPet(Object body) {
        return put(
                getDefaultJsonRequestSpecification(), Endpoints.PET, body);
    }

    public Response deletePetById(Long id) {
        return delete(
                getJsonRequestSpecificationWithPathParam("petId", id), Endpoints.PET_PET_ID);
    }

}
