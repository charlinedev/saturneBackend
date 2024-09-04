package com.saturne.repositories;

import com.saturne.entities.Catalogue;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CatalogueRepository implements PanacheRepository<Catalogue> {

  public Optional<Catalogue> findCatalogueByIdCatalogue(int id) {
    return find("idCatalogue", id).firstResultOptional();
  }

}
