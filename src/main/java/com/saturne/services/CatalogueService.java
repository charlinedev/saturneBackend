package com.saturne.services;

import com.saturne.entities.Catalogue;
import com.saturne.exceptions.CatalogueNotFoundException;
import com.saturne.repositories.CatalogueRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CatalogueService {

  //Injection de dépendance
  @Inject
  private CatalogueRepository catalogueRepository;

  //Ajouter un catalogue
  @Transactional
  public Catalogue addCatalogue(Catalogue catalogue) {
    //catalogue.setCatalogueCode(UUID.randomUUID().toString());
    catalogueRepository.persist(catalogue);
    return catalogue;
  }

  //Rechercher tous les catalogues et les afficher @param reference
  @Transactional
  public List<Catalogue> findAllCatalogues() {
    return catalogueRepository.listAll();
  }

  //Rechercher un catalogue et l'afficher @param reference
  @Transactional
  public Catalogue findCatalogueById(int id) {
    return catalogueRepository
      .findCatalogueByIdCatalogue(id)
      .orElseThrow(() -> new CatalogueNotFoundException("Catalogue by id " + id + "was not found"));
  }
  //Supprimer une formation @param idFormation

}
