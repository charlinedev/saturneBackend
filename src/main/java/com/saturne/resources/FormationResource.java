package com.saturne.resources;

import com.saturne.dto.FormationDTO;
import com.saturne.entities.Formation;
import com.saturne.services.FormationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/training")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FormationResource {

  @Inject
  private FormationService sf;

  public FormationResource(FormationService sf) {
    this.sf = sf;
  }

  @GET
  @Path("/all")
  public Response getAllFormations() {
    List<FormationDTO> formations = sf.findAllFormations();
    return Response.ok(formations).build();
  }

  //  @GetMapping("/all/themes")
  //  public ResponseEntity<List<Theme>> getAllThemes() {
  //	  List<Formation> formations = sf.findAllFormations();
  //	  for(int i=0; ; i++)
  //    List<Theme> allThemes ;
  //		  //allThemes= sf.findAllFormations().for;
  //    return new ResponseEntity<>(allThemes, HttpStatus.OK);
  //  }

  @GET
  @Path ("findbyId/{id}")
  public Response getFormationById(@PathParam("id") long id) {
    FormationDTO formationDTO = sf.findFormationById(id);
    return Response.ok(formationDTO).build();
  }

  @GET
  @Path("/findRef/{ref}")
  public Response getFormationByReference(@PathParam("ref") String ref) {
    FormationDTO formationDTO = sf.findFormationByReference(ref);
    return Response.ok(formationDTO).build();
  }

  @GET
  @Path("/findKeyword/{keyword}")
  public Response getFormationsByKeyword(@PathParam("keyword") String keyword) {
    List<FormationDTO> formations = sf.findByKeyword(keyword);
    return Response.ok(formations).build();
  }

  /***
   *
   * @param newf
   * @return
   */
  @POST
  @Path ("/add")
  @Consumes( MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response addFormation(FormationDTO newf) {
    try {
      FormationDTO createdFormation = sf.addFormation(newf);
      return Response.status(Response.Status.CREATED).entity(createdFormation).build();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
    }
  }

  @PUT
  @Path("/update/{id}")
  public Response updateFormation(@PathParam("id") long id, FormationDTO uf) {
    try {
      FormationDTO updatedFormation = sf.updateFormation(id, uf);
      return Response.ok(updatedFormation).build();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
    }
  }

  @DELETE
  @Path("/delete/{id}")
  public Response deleteFormation(@PathParam("id") long id) {
    sf.deleteFormation(id);
    return Response.status(Response.Status.OK).build();
  }
}
