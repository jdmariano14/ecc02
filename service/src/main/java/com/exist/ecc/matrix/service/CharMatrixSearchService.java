package com.exist.ecc.matrix.service;

import java.util.List;
import java.util.ArrayList;

import com.exist.ecc.matrix.model.api.CharMatrix;

public class CharMatrixSearchService {
  public List<int[][]> getQueryOccurrences(CharMatrix matrix, String query) {
    List occ = new ArrayList(matrix.rows());

    for (int row = 0; row < matrix.rows(); row++) {
      int[][] rowOcc = new int[matrix.cols(row)][2];

      for (int col = 0; col < matrix.cols(row); col++) {
        rowOcc[col][0] = getQueryOccurrencesInString(matrix.getKey(row, col), query);
        rowOcc[col][1] = getQueryOccurrencesInString(matrix.getValue(row, col), query);
      }

      occ.add(rowOcc);
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