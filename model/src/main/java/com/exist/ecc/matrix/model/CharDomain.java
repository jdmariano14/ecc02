package com.exist.ecc.matrix.model;

public abstract class CharDomain {

  public char getRandomChar() {
    char randomChar;
    do {
      randomChar = (char)(Math.random() * 256);
    } while (!isInDomain(randomChar));

    return randomChar;
  }

  public abstract boolean isInDomain(char c);
}