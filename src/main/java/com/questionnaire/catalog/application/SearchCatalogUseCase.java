package com.questionnaire.catalog.application;

import com.questionnaire.catalog.domain.entity.Catalog;
import com.questionnaire.catalog.domain.service.CatalogService;

import java.util.Optional;

public class SearchCatalogUseCase {
    private final CatalogService catalogService;

    public SearchCatalogUseCase(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public Optional<Catalog> execute(int id) {
        return catalogService.findCatalogById(id);
    }
}
