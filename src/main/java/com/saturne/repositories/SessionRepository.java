package com.saturne.repositories;

import com.saturne.entities.Session;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class SessionRepository implements PanacheRepository<Session> {

  public Optional<Session> getSessionByIdSession(long idSession) {
    return find("idSession", idSession).firstResultOptional();
  }

  public void deleteByIdSession(long idSession){
    delete("idSession", idSession);
  }
}
