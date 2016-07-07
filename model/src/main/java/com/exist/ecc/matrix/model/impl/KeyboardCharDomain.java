package com.exist.ecc.matrix.model.impl;

import com.exist.ecc.matrix.model.api.CharDomain;

public class KeyboardCharDomain implements CharDomain {
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

  public boolean contains(char c) {
    return allowedChars.toString().indexOf(c) >= 0;
  }
}