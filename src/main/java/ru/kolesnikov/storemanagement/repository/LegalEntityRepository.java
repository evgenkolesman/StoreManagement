package ru.kolesnikov.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolesnikov.storemanagement.model.LegalEntity;

public interface LegalEntityRepository extends JpaRepository<LegalEntity, Long> {
}
