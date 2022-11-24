package ru.kolesnikov.storemanagement.controller.items;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.kolesnikov.storemanagement.controller.TestAbstractIntegration;
import ru.kolesnikov.storemanagement.controller.container.ItemsMethods;
import ru.kolesnikov.storemanagement.controller.dto.item.ItemDTORequest;
import ru.kolesnikov.storemanagement.controller.dto.item.ItemDTOResponse;

import java.math.BigDecimal;

public class ItemsTests extends TestAbstractIntegration {

    @Autowired
    private ItemsMethods itemsMethods;

    @LocalServerPort
    private int port;

    @BeforeEach
    void testDataProduce() {
        RestAssured.port = port;
    }

    @Test
    void addDeleteTest() {
        var response = itemsMethods.addItem(new ItemDTORequest("Name",
                        "1233245",
                        new BigDecimal("12.2")))
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(ItemDTOResponse.class);

        itemsMethods.deleteItem(response.id())
                .assertThat()
                .statusCode(204);


    }
}
