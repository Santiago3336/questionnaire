package com.questionnaire.catalog.application;

import com.questionnaire.catalog.domain.service.CatalogService;

public class DeleteCatalogUseCase {
    private final CatalogService catalogService;

    public DeleteCatalogUseCase (CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public void execute(int id) {
        catalogService.deleteCatalog(id);
    }
}
