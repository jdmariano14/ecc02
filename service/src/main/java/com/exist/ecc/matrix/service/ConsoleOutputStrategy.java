package com.exist.ecc.matrix.service;

import com.exist.ecc.matrix.model.AsciiMatrix;

public class ConsoleOutputStrategy implements AsciiMatrixOutputStrategy {
  public void writeMatrix(AsciiMatrix matrix) {
    System.out.println(matrix);
  }
}