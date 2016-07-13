package com.exist.ecc.matrix.service;

import java.util.List;
import java.util.ArrayList;

import com.exist.ecc.matrix.model.api.CharMatrix;

public class CharMatrixSearchService {
  public int getQueryOccurrencesInKey(CharMatrix matrix, String query, int row, int col)
        throws IllegalArgumentException {
    validateRowIndex(matrix, row);
    validateColumnIndex(matrix, row, col);

    return getQueryOccurrencesInString(matrix.getKey(row, col), query);
  }

  public int getQueryOccurrencesInValue(CharMatrix matrix, String query, int row, int col)
        throws IllegalArgumentException {
    validateRowIndex(matrix, row);
    validateColumnIndex(matrix, row, col);

    return getQueryOccurrencesInString(matrix.getValue(row, col), query);
  }

  private int getQueryOccurrencesInString(String input, String query) {
    int occ = 0;

    for (int pos = 0; pos <= input.length() - query.length(); pos++) {
      String substr = input.substring(pos, pos + query.length());

      if (substr.equals(query)) {
        occ++;
      }
    }

    return occ;
  }

  private void validateRowIndex(CharMatrix matrix, int row) throws IllegalArgumentException {
    if (row >= matrix.rows()) {
      throw new IllegalArgumentException("row index out of bounds");
    }

    if (row < 0) {
      throw new IllegalArgumentException("negative row index not allowed");
    }
  }

  private void validateColumnIndex(CharMatrix matrix, int row, int col) throws IllegalArgumentException {
    if (col >= matrix.cols(row)) {
      throw new IllegalArgumentException("column index out of bounds");
    }

    if (col < 0) {
      throw new IllegalArgumentException("negative column index not allowed");
    }
  }
}