package com.petstore.specs;

import com.petstore.config.Configuration;
import com.petstore.config.ConfigurationManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import java.util.List;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;

@Getter
public class RequestSpecificationHelper {

    private final static Configuration configuration = ConfigurationManager.getConfiguration();
    public static final String APPLICATION_JSON = "application/json";

    public static RequestSpecification getDefaultJsonRequestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(APPLICATION_JSON)
                .setAccept(APPLICATION_JSON)
                .build();
    }

    public static RequestSpecification getJsonRequestSpecificationWithPathParam(String pathParamName, Object pathParamValue) {
        return new RequestSpecBuilder()
                .setContentType(APPLICATION_JSON)
                .setAccept(APPLICATION_JSON)
                .addPathParam(pathParamName, pathParamValue)
                .build();
    }

    public static RequestSpecification getJsonRequestSpecificationWithParams(
            Map<String, List<?>> multiQueryParams
    ) {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setContentType(APPLICATION_JSON)
                .setAccept(APPLICATION_JSON);

        if (multiQueryParams != null && !multiQueryParams.isEmpty()) {
            for (Map.Entry<String, List<?>> entry : multiQueryParams.entrySet()) {
                builder.addQueryParam(entry.getKey(), entry.getValue());
            }
        }

        return builder.build();
    }

}
