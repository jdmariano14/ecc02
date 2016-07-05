package com.exist.ecc.matrix.model.impl;

import java.util.Set;
import java.util.HashSet;

public class AsciiMatrixConventions {
  public static final int DEFAULT_ELEMENT_LENGTH = 3;

  public static final char TEXT_ELEMENT_DELIMITER = ',';
  public static final char TEXT_CELL_DELIMITER = '|';

  private static int cellSize = 2;
  private static CharDomain domain;

  static {
    domain = new AsciiCharDomain();
  }

  public static void setDomain(CharDomain d) {
    domain = d;
  }

  public static int getCellSize() {
    return cellSize;
  }

  public static void setCellSize(int size) {
    cellSize = size;
  }

  public static char getRandomChar() {
    return domain.getRandomChar();
  }

  public static boolean isIllegal(char c) {
    return !domain.isInDomain(c);
  }

  public static String textElementDivider() {
    return TEXT_ELEMENT_DELIMITER + " ";
  }

  public static String textCellDivider() {
    return "  " + TEXT_CELL_DELIMITER + "  ";
  }

}