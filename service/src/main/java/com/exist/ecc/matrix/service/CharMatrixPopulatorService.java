package com.exist.ecc.matrix.service;

import com.exist.ecc.matrix.model.api.CharMatrix;
import com.exist.ecc.matrix.model.api.CharDomain;

public class CharMatrixPopulatorService {
  public void populate(CharMatrix matrix, int rows, int cols, int length) throws IllegalArgumentException {
    validateRowCount(rows);
    validateColCount(cols);

    matrix.clear();

    while (matrix.rows() < rows) {
      matrix.addRow();
    }

    for (int row = 0; row < rows; rows++) {
      for (int col = 0; col < cols; col++) {
        String key = generateRandomContent(matrix, length);
        String value = generateRandomContent(matrix, length);

        matrix.put(row, key, value);
      }
    }

  }

  private String generateRandomContent(CharMatrix matrix, int length) {
    StringBuilder sb = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      sb.append(matrix.getDomain().getRandomChar());
    }

    return sb.toString();
  }

  private void validateRowCount(int rows) throws IllegalArgumentException {
    if (rows < 0) {
      throw new IllegalArgumentException("negative rows not allowed");
    }
  }

  private void validateColCount(int cols) throws IllegalArgumentException {
    if (cols < 0) {
      throw new IllegalArgumentException("negative columns not allowed");
    }
  }
}