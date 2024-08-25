package com.questionnaire.catalog.application;

import com.questionnaire.catalog.domain.entity.Catalog;
import com.questionnaire.catalog.domain.service.CatalogService;

public class CreateCatalogUseCase {
    private final CatalogService catalogService;

    public CreateCatalogUseCase (CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public void execute (Catalog catalog) {
        catalogService.createCatalog(catalog);
    }
}
