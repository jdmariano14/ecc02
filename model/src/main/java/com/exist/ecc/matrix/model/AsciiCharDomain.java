package com.exist.ecc.matrix.model;

public class AsciiCharDomain extends CharDomain {

  public boolean isInDomain(char c) {
    return CharacterHelper.isAscii(c);
  }
  
}