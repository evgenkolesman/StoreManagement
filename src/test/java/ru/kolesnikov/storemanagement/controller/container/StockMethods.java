package ru.kolesnikov.storemanagement.controller.container;

import io.restassured.response.ValidatableResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import ru.kolesnikov.storemanagement.controller.dto.stock.StockDTORequest;
import ru.kolesnikov.storemanagement.utils.UriComponentsBuilderUtil;

import static io.restassured.RestAssured.given;

@Component
public class StockMethods {

    private final static String STOCK_ENDPOINT = "/api/v1/stock";
    private final static String STOCK_ENDPOINT_WITH_ID = "/api/v1/stock/%s";


    public ValidatableResponse addStock(StockDTORequest message) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(message)
                .when()
                .post(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(STOCK_ENDPOINT))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

    public ValidatableResponse updateStock(Long id,
                                           StockDTORequest message) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(message)
                .when()
                .put(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(STOCK_ENDPOINT_WITH_ID, id.toString()))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

    public ValidatableResponse deleteStock(Long id) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(STOCK_ENDPOINT_WITH_ID, String.valueOf(id)))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

    public ValidatableResponse getStockById(Long id) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(STOCK_ENDPOINT_WITH_ID, id))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

    public ValidatableResponse getStocks() {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(UriComponentsBuilderUtil
                        .builder()
                        .replacePath(String.format(STOCK_ENDPOINT))
                        .toUriString())
                .then()
                .and().log()
                .all();

    }

}
