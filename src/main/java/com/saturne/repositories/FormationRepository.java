package com.saturne.repositories;

import com.saturne.dto.FormationDTO;
import com.saturne.entities.Formation;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class FormationRepository  implements PanacheRepository<Formation> {

  //Optional<Formation> findFormationById(Long id);
  public Optional<Formation> findFormationByIdFormation(long idFormation) {
    return find("idFormation", idFormation).firstResultOptional();
  }


  public Optional<Formation> findFormationByReference(String refFormation) {
    return find("refFormation", refFormation).firstResultOptional();
  }

  //List<Formation> findByKeyword(String keyword);
  public void deleteFormationByIdFormation(long idFormation) {
    delete("idFormation", idFormation);
  }
}
