package com.exist.ecc.matrix.model.api;

public interface CharDomain {

  public default char getRandomChar() {
    char randomChar;

    do {
      randomChar = (char)(Math.random() * 256);
    } while (!contains(randomChar));

    return randomChar;
  }

  public abstract boolean contains(char c);
}