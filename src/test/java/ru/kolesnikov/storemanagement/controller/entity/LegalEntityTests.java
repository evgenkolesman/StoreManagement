package ru.kolesnikov.storemanagement.controller.entity;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.kolesnikov.storemanagement.controller.TestAbstractIntegration;
import ru.kolesnikov.storemanagement.controller.container.LegalEntityMethods;
import ru.kolesnikov.storemanagement.controller.dto.legalentity.LegalEntityDTORequest;
import ru.kolesnikov.storemanagement.controller.dto.legalentity.LegalEntityDTOResponse;

public class LegalEntityTests extends TestAbstractIntegration {

    @Autowired
    private LegalEntityMethods legalEntityMethods;

    @LocalServerPort
    private int port;

    @BeforeEach
    void testDataProduce() {
        RestAssured.port = port;
    }

    @Test
    void addDeleteTest() {
        var response = legalEntityMethods.addLegalEntity(new LegalEntityDTORequest("Name",
                        "addres",
                        "123456781"))
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(LegalEntityDTOResponse.class);

        legalEntityMethods.deleteLegalEntity(response.id())
                .assertThat()
                .statusCode(204);


    }
}
