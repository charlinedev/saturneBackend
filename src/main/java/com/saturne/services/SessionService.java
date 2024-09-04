package com.saturne.services;

import com.saturne.entities.Session;
import com.saturne.repositories.SessionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class SessionService {

  @Inject
  private SessionRepository sessionRepository;

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  public Session createSession(Session s) {
    sessionRepository.persist(s);
    return s;
  }

  @Transactional
  public Session updateSession(Session s) {
    entityManager.merge(s);
    return s;
  }

  @Transactional
  public Session getSession(long id) {
    return sessionRepository.getSessionByIdSession(id).orElseThrow(RuntimeException::new);
  }

  @Transactional
  public List<Session> getSessions(HashMap<String, Object> params) {
    Stream<Session> sessionsStream = sessionRepository.findAll().stream();
    if (params.containsKey("idTraining")) {
      sessionsStream =
        sessionsStream.filter(s -> {
          if (s.getFormation() != null) {
            return s.getFormation().getIdFormation() == Float.parseFloat(params.get("idTraining").toString());
          }
          return false;
        });
    }
    if (params.containsKey("dateStart")) {
      sessionsStream =
        sessionsStream.filter(s -> {
          LocalDate dateStart = LocalDate.parse(params.get("dateStart").toString());
          return dateStart.isBefore(s.getDateDebut());
        });
    }
    if (params.containsKey("dateEnd")) {
      sessionsStream =
        sessionsStream.filter(s -> {
          LocalDate dateEnd = LocalDate.parse(params.get("dateEnd").toString());
          return dateEnd.isAfter(s.getDateFin());
        });
    }
    if (params.containsKey("location")) {
      sessionsStream =
        sessionsStream.filter(s -> {
          return s.getLieu().toUpperCase().contains(params.get("location").toString().toUpperCase());
        });
    }
    if (params.containsKey("price")) {
      sessionsStream =
        sessionsStream.filter(s -> {
          return s.getPrix() < Float.parseFloat(params.get("price").toString());
        });
    }
    return sessionsStream.collect(Collectors.toList());
  }
  @Transactional
  public void deleteSession(long id) {
    sessionRepository.deleteByIdSession(id);
  }

  @Transactional
  public List<Session> saveAll(List<Session> listSessions) {
    for (Session session : listSessions) {
      if (session.getIdSession() == null) {
        sessionRepository.persist(session); // new session
      } else {
        entityManager.merge(session); // existing session
      }
    }
    return listSessions;
  }
}
