package com.saturne.exceptions;

@SuppressWarnings("serial")
public class CatalogueNotFoundException extends RuntimeException {

  public CatalogueNotFoundException(String message) {
    super(message);
  }
}
