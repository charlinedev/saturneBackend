package com.saturne.resources;

import com.saturne.entities.Theme;
import com.saturne.services.ThemeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/themes")
public class ThemeResource {

  @Inject
  private final ThemeService ts;

  public ThemeResource(ThemeService ts) {
    this.ts = ts;
  }

  @GET
  @Path ("/all")
  public Response getAllThemes() {
    List<Theme> themes = ts.findAllThemes();
    return Response.ok(themes).build();
  }

  @GET
  @Path ("findbyId/{id}")
  public Response getThemeById(@PathParam("id") long id) {
    Theme theme = ts.findThemeById(id);
    if (theme != null) {
      return Response.ok(theme).build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).entity("Theme not found").build();
    }
  }

  @DELETE
  @Path("/delete/{id}")
  public Response deleteTheme(@PathParam("id") long id) {
    ts.deleteTheme(id);
    return Response.ok().build();
  }
}
