package com.bank.customer.onboarding.service.impl;

import com.bank.customer.onboarding.model.entity.IdDocument;
import com.bank.customer.onboarding.repository.IdDocumentRepository;
import com.bank.customer.onboarding.service.IdDocumentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IdDocumentServiceImpl implements IdDocumentService {

    private final IdDocumentRepository idDocumentRepository;

    @Override
    public IdDocument save(IdDocument idDocument) {
        return idDocumentRepository.save(idDocument);
    }
}
