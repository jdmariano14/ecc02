package com.exist.ecc.matrix.model.impl;

public class KeyboardCharDomain extends CharDomain {
  private StringBuilder allowedChars;

  public KeyboardCharDomain() {
    allowedChars = new StringBuilder();

    allowedChars.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    allowedChars.append("abcdefghijklmnopqrstuvwxyz");
    allowedChars.append("1234567890");
    allowedChars.append("!@#$%^&*()_");
    allowedChars.append("-=_+[]{}\\|;':\",.<>/?`~");
    allowedChars.append(" ");
    allowedChars.append("\t");
    allowedChars.append(System.lineSeparator());
  }

  public boolean isInDomain(char c) {
    return allowedChars.toString().indexOf(c) >= 0;
  }
  
}