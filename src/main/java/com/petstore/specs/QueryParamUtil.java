package com.petstore.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class QueryParamUtil {
    public static RequestSpecification createSpec(String baseUri, String basePath, Map<String, Object> queryParams) {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(basePath);

        if (queryParams != null && !queryParams.isEmpty()) {
            builder.addQueryParams(queryParams);
        }

        return builder.build();
    }
}
