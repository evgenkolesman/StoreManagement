package ru.kolesnikov.storemanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikov.storemanagement.exceptions.NotFoundLegalEntityException;
import ru.kolesnikov.storemanagement.model.LegalEntity;
import ru.kolesnikov.storemanagement.repository.LegalEntityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LegalEntityService {

    private final LegalEntityRepository legalEntityRepository;

    public List<LegalEntity> getLegalEntities() {
        return legalEntityRepository.findAll();
    }

    public LegalEntity getLegalEntityById(Long entityId) {
        return legalEntityRepository.findById(entityId).orElseThrow(() -> new NotFoundLegalEntityException(entityId));
    }

    public LegalEntity addLegalEntity(LegalEntity legalEntity) {
        return legalEntityRepository.save(legalEntity);
    }

    public LegalEntity updateLegalEntity(Long id, LegalEntity legalEntity) {
        if (!legalEntityRepository.existsById(id)) {
            throw new NotFoundLegalEntityException(id);
        }
        return legalEntityRepository.save(legalEntity);
    }

    public void deleteLegalEntity(Long id) {
        if (!legalEntityRepository.existsById(id)) {
            throw new NotFoundLegalEntityException(id);
        }
        legalEntityRepository.deleteById(id);
    }

}
