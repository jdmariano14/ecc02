package com.exist.ecc.matrix.service.impl;

import com.exist.ecc.matrix.model.impl.AsciiMatrix;

public class ConsoleOutputStrategy implements AsciiMatrixOutputStrategy {
  public void writeMatrix(AsciiMatrix matrix) {
    System.out.println(matrix);
  }
}