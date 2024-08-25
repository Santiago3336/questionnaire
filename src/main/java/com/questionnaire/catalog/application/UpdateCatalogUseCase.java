package com.questionnaire.catalog.application;

import com.questionnaire.catalog.domain.entity.Catalog;
import com.questionnaire.catalog.domain.service.CatalogService;

public class UpdateCatalogUseCase {
    private final CatalogService catalogService;

    public UpdateCatalogUseCase (CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public void execute(Catalog catalog) {
        catalogService.updateCatalog(catalog);
    }
}
