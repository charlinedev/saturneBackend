package com.saturne.repositories;

import com.saturne.entities.Theme;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ThemeRepository implements PanacheRepository<Theme> {

  public Optional<Theme> findThemeByIdTheme(long idTheme) {
    return find("idTheme", idTheme).firstResultOptional();
  }

  public void deleteThemeByIdTheme(long idTheme){
    delete("idTheme", idTheme);
  }
}
