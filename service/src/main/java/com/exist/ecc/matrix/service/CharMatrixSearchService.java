package com.exist.ecc.matrix.service;

import com.exist.ecc.matrix.model.api.CharMatrix;

public class CharMatrixSearchService {
  public int[][][] getQueryOccurrences(CharMatrix matrix, String query) {
    int[][][] occ = new int[matrix.rows()][matrix.cols()][2];

    for (int row = 0; row < matrix.rows(); row++) {
      for (int col = 0; col < matrix.cols(); col++) {
        occ[row][col][0] = getQueryOccurrencesInString(matrix.getKey(row, col), query);
        occ[row][col][1] = getQueryOccurrencesInString(matrix.getValue(row, col), query);
      }
    }

    return occ;
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
}