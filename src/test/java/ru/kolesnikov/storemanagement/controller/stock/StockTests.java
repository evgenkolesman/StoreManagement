package ru.kolesnikov.storemanagement.controller.stock;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.kolesnikov.storemanagement.controller.TestAbstractIntegration;
import ru.kolesnikov.storemanagement.controller.container.StockMethods;
import ru.kolesnikov.storemanagement.controller.dto.stock.StockDTORequest;
import ru.kolesnikov.storemanagement.controller.dto.stock.StockDTOResponse;

public class StockTests extends TestAbstractIntegration {

    @Autowired
    private StockMethods stockMethods;

    @LocalServerPort
    private int port;

    @BeforeEach
    void testDataProduce() {
        RestAssured.port = port;
    }

    @Test
    void addDeleteTest() {
        var response = stockMethods.addStock(new StockDTORequest("Name", "addres"))
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(StockDTOResponse.class);

        stockMethods.deleteStock(response.id())
                .assertThat()
                .statusCode(204);


    }
}
