package com.vodafone.onionpatterndemo.errorhandling;

public class ProdutNotFoundException extends RuntimeException {
  public ProdutNotFoundException(String message) {
    super(message);
  }
}
