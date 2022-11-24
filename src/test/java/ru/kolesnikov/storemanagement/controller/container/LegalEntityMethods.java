package ru.kolesnikov.storemanagement.controller.container;

import io.restassured.response.ValidatableResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import ru.kolesnikov.storemanagement.controller.dto.legalentity.LegalEntityDTORequest;
import ru.kolesnikov.storemanagement.utils.UriComponentsBuilderUtil;

import static io.restassured.RestAssured.given;

@Component
public class LegalEntityMethods {

    private final static String LEGAL_ENTITY_ENDPOINT = "/api/v1/legalEntity";
    private final static String LEGAL_ENTITY_ENDPOINT_ID = "/api/v1/legalEntity/%s";


    public ValidatableResponse addLegalEntity(LegalEntityDTORequest message) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(message)
                .when()
                .post(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(LEGAL_ENTITY_ENDPOINT))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

    public ValidatableResponse updateLegalEntity(Long id,
                                                 LegalEntityDTORequest message) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(message)
                .when()
                .put(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(LEGAL_ENTITY_ENDPOINT_ID, id))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

    public ValidatableResponse deleteLegalEntity(Long id) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(LEGAL_ENTITY_ENDPOINT_ID, id))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

    public ValidatableResponse getLegalEntity(Long id) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(LEGAL_ENTITY_ENDPOINT_ID, id))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

    public ValidatableResponse getLegalEntities() {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(LEGAL_ENTITY_ENDPOINT))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

}
