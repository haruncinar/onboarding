package com.bank.customer.onboarding.repository;

import com.bank.customer.onboarding.model.entity.IdDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdDocumentRepository extends JpaRepository<IdDocument, Long> {
}
