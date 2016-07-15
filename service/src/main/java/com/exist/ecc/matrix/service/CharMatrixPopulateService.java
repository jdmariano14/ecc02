package com.exist.ecc.matrix.service;

import com.exist.ecc.matrix.model.api.CharMatrix;
import com.exist.ecc.matrix.model.api.CharDomain;

public class CharMatrixPopulateService {
  public void populate(CharMatrix matrix, int rows, int cols, int length) throws IllegalArgumentException {
    matrix.clear();

    for (int row = 0; row < rows; row++) {
      matrix.addRow();

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
}