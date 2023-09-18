package com.undina.deal.controller;

import com.undina.deal.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.DocumentControllerApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DocumentController implements DocumentControllerApi {

    private final DocumentService documentService;

    @Override
    public ResponseEntity<Void> sendDocuments(Long applicationId) {
        log.info("sendDocuments - start: applicationId = {}", applicationId);
        documentService.sendDocuments(applicationId);
        log.info("sendDocuments - result: OK");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> signDocuments(Long applicationId) {
        log.info("signDocuments - start: applicationId = {}", applicationId);
        documentService.signDocuments(applicationId);
        log.info("signDocuments - result: OK");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> codeDocuments(Long applicationId, Integer sesCode) {
        log.info("codeDocuments - start: applicationId = {}", applicationId);
        documentService.codeDocuments(applicationId, sesCode);
        log.info("codeDocuments - result: OK");
        return ResponseEntity.ok().build();
    }
}
