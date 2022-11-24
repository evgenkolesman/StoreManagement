package ru.kolesnikov.storemanagement.controller.container;

import io.restassured.response.ValidatableResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import ru.kolesnikov.storemanagement.controller.dto.item.ItemDTORequest;
import ru.kolesnikov.storemanagement.utils.UriComponentsBuilderUtil;

import static io.restassured.RestAssured.given;

@Component
public class ItemsMethods {

    private final static String ITEMS_ENDPOINT = "/api/v1/items";
    private final static String ITEMS_ENDPOINT_WITH_ID = "/api/v1/items/%s";


    public ValidatableResponse addItem(ItemDTORequest message) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(message)
                .when()
                .post(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(ITEMS_ENDPOINT))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

    public ValidatableResponse updateItem(Long id,
                                          ItemDTORequest message) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(message)
                .when()
                .put(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(ITEMS_ENDPOINT_WITH_ID, id.toString()))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

    public ValidatableResponse deleteItem(Long id) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(ITEMS_ENDPOINT_WITH_ID, String.valueOf(id)))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

    public ValidatableResponse getItem(Long id) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(ITEMS_ENDPOINT_WITH_ID, id))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

    public ValidatableResponse getItem() {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(ITEMS_ENDPOINT))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

}
