package ru.kolesnikov.storemanagement.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kolesnikov.storemanagement.controller.dto.legalentity.LegalEntityDTORequest;
import ru.kolesnikov.storemanagement.controller.dto.legalentity.LegalEntityDTOResponse;
import ru.kolesnikov.storemanagement.model.LegalEntity;
import ru.kolesnikov.storemanagement.service.LegalEntityService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LegalEntityController {

    private final LegalEntityService legalEntityService;

    @GetMapping("/api/v1/legalEntity")
    public List<LegalEntityDTOResponse> getLegalEntities() {
        return legalEntityService.getLegalEntities().stream()
                .map(legalEntity -> new LegalEntityDTOResponse(legalEntity.getId(),
                        legalEntity.getEntityName(),
                        legalEntity.getAddress()))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v1/legalEntity/{entityId}")
    public LegalEntityDTOResponse getLegalEntity(@PathVariable("entityId") Long entityId) {
        LegalEntity legalEntity = legalEntityService.getLegalEntityById(entityId);
        return new LegalEntityDTOResponse(legalEntity.getId(), legalEntity.getEntityName(), legalEntity.getAddress());
    }

    @PostMapping("/api/v1/legalEntity")
    public LegalEntityDTOResponse addLegalEntity(@RequestBody @NonNull LegalEntityDTORequest legalEntityDTORequest) {
        LegalEntity legalEntity = legalEntityService.addLegalEntity(new LegalEntity(legalEntityDTORequest.name(),
                legalEntityDTORequest.address(), legalEntityDTORequest.inn()));
        return new LegalEntityDTOResponse(legalEntity.getId(), legalEntity.getEntityName(), legalEntity.getAddress());
    }

    @PutMapping("/api/v1/legalEntity/{entityId}")
    public LegalEntityDTOResponse updateLegalEntity(@PathVariable("entityId") Long entityId,
                                                    @RequestBody LegalEntityDTORequest legalEntityDTORequest) {
        LegalEntity legalEntity = legalEntityService.updateLegalEntity(entityId,
                new LegalEntity(entityId,
                        legalEntityDTORequest.name(),
                        legalEntityDTORequest.address(),
                        legalEntityDTORequest.inn()));
        return new LegalEntityDTOResponse(legalEntity.getId(), legalEntity.getEntityName(), legalEntity.getAddress());

    }

    @DeleteMapping("/api/v1/legalEntity/{entityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLegalEntity(@PathVariable("entityId") Long stockId) {
        legalEntityService.deleteLegalEntity(stockId);
    }


}
