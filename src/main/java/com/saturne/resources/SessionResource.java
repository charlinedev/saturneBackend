package com.saturne.resources;

import com.saturne.dto.FormationDTO;
import com.saturne.dto.SessionDTO;
import com.saturne.entities.Formation;
import com.saturne.entities.Session;
import com.saturne.services.FormationService;
import com.saturne.services.SessionService;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;

@Path("/api/session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessionResource {

  @Inject
  private FormationService formationService;
  @Inject
  private SessionService sessionService;

  private static final Logger log = LoggerFactory.getLogger(SessionResource.class);


  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSessions(
    @QueryParam("idTraining") @DefaultValue ("0") long idTraining,
    @QueryParam("dateStart") String dateStart,
    @QueryParam("dateEnd") String dateEnd,
    @QueryParam("location") String location,
    @QueryParam("price") String price
  ) {
    HashMap<String, Object> params = new HashMap<>();
    if (idTraining > 0) {
      params.put("idTraining", idTraining);
    }
    if (dateStart != null && this.isLocalDate(dateStart)) {
      params.put("dateStart", LocalDate.parse(dateStart, DateTimeFormatter.ISO_LOCAL_DATE));
    }
    if (dateEnd != null && this.isLocalDate(dateEnd)) {
      params.put("dateEnd", LocalDate.parse(dateEnd, DateTimeFormatter.ISO_LOCAL_DATE));
    }
    if (location != null) {
      params.put("location", location);
    }
    if (price != null/* && this.isFloat(price)*/) {
      params.put("price", Float.parseFloat(price));
    }
    return Response.ok(sessionService.getSessions(params)).build();
  }

  @GET
  @Path ("/{id}")
  @Produces (MediaType.APPLICATION_JSON)
  public Response getSessionById(@PathParam("id") long id) {
    Session s = null;
    try {
      s = sessionService.getSession(id);
      return Response.ok(s).build();
    } catch (EntityNotFoundException e) {
      throw new PersistenceException("Error: Cannot FIND SESSION.");
    }
  }

  @POST
  @Path("/add/{idFormation}")
  @Consumes ( MediaType.APPLICATION_JSON )
  public Response createSession(SessionDTO sessionDTO, @PathParam("idFormation") long idFormation) {
    FormationDTO formationDTO = formationService.findFormationById(idFormation);
    Formation formation = formationService.toEntity(formationDTO);
    log.trace("Found the training n°: " + idFormation + " => " + formation);
    try {
      //System.out.println(session);
      Session session = toEntity(sessionDTO);
      session.setFormation(formation);
      session = sessionService.createSession(session);
      log.trace("session before update: " + session + "; " + session.getFormation());
      session.setFormation(formation);
      sessionService.updateSession(session);
      log.trace("session after update: " + session + "; " + session.getFormation());
      return Response.status(Response.Status.CREATED).entity(session).build();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  private Session toEntity(SessionDTO sessionDTO) {
    Session session = new Session();
    session.setIdSession(sessionDTO.getIdSession());
    session.setDateDebut(sessionDTO.getDateDebut());
    session.setDateFin(sessionDTO.getDateFin());
    session.setLieu(sessionDTO.getLieu());
    session.setPrix(sessionDTO.getPrix());
    // Assurez-vous de définir toutes les propriétés nécessaires
    return session;
  }

  @PUT
  @Path ( "/{id}")
  @Produces ( MediaType.APPLICATION_JSON )
  public Response updateSession(
    @PathParam ("id") long idSession,
    @QueryParam(value ="dateStart") String dateStart,
    @QueryParam(value ="dateEnd") String dateEnd,
    @QueryParam(value ="location") String location,
    @QueryParam(value ="price") @DefaultValue ("0") String price,
    @QueryParam(value ="idClassroom") @DefaultValue ("0") long idClassroom,
    @QueryParam(value ="idTrainer") @DefaultValue ("0") long idTrainer,
    @QueryParam(value ="evalSessions[]") @DefaultValue ("[]") String[] evalSessions,
    @QueryParam(value ="stagiaires[]") @DefaultValue ("[]") String[] stagiaires
  ) {
    try {
      Session s = sessionService.getSession(idSession);
      DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
      if (dateStart != null) {
        try {
          LocalDate parsedDateStart = LocalDate.parse(dateStart, formatter);
          s.setDateDebut(parsedDateStart);
        } catch (DateTimeParseException e) {
          throw new DateTimeException("Error: Invalid Date Format 'dateStart'.");
        }
      }
      if (dateEnd != null) {
        try {
          LocalDate parsedDateEnd = LocalDate.parse(dateEnd, formatter);
          s.setDateFin(parsedDateEnd);
        } catch (DateTimeParseException e) {
          throw new DateTimeException("Error: Invalid Date Format 'dateEnd'.");
        }
      }
      if (location != null) {
        s.setLieu(location);
      }
      if (this.isFloat(price) && Float.parseFloat(price) > 0.0f) {
        s.setPrix(Float.parseFloat(price));
      }
      if (idClassroom > 0) {
        //SalleService ss = new SalleService();
        //Salle classroom = ss.getSessionByIdSession(idClassroom);
        //s.setSalle(classroom);
      }
      if (idTrainer > 0) {
        //FormateurService fs = new FormateurService();
        //Formateur trainer = ss.getReferenceById(idTrainer);
        //s.setFormateur(trainer);
      }
      if (evalSessions.length > 0) {
        //EvalSessionService ess = new EvalSessionService();
        //List<EvalSession> evalSession = ess.findAllById(idEval);
        //s.setEvalSessions(evalSession);
      }
      if (stagiaires.length > 0) {
        //StagiaireService ss = new StagiaireService();
        //List<Stagiaire> trainee = ss.findAllById(idTrainee);
        //s.setStagiaires(trainee);
      }
      return Response.ok(sessionService.updateSession(s)).build();
    } catch (EntityNotFoundException e) {
      return Response.status(Response.Status.NOT_FOUND).entity("Error: Cannot FIND Session.").build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: Unable to update session.").build();
    }
  }

  @DELETE
  @Path("/{id}")
  @Consumes (MediaType.APPLICATION_JSON)
  public Response deleteSessionById(@PathParam("id") long id) {
    try {
      Session s = sessionService.getSession(id);
      log.trace("session: " + s);
      sessionService.deleteSession(s.getIdSession());
      log.trace("session deleted");
      return Response.noContent().build();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: Unable to delete session.").build();
    }
  }

  private boolean isFloat(String nbStr) {
    try {
      Float.parseFloat(nbStr);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  private boolean isLocalDate(String dateStr) {
    try {
      LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
    } catch (DateTimeParseException e) {
      return false;
    }
    return true;
  }
}
