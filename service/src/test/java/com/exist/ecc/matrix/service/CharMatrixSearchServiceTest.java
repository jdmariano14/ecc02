package com.exist.ecc.matrix.service;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

import com.exist.ecc.matrix.model.api.CharMatrix;
import com.exist.ecc.matrix.model.api.CharDomain;

import com.exist.ecc.matrix.model.api.BaseDummyCharMatrix;

public class CharMatrixSearchServiceTest {
  CharMatrix matrix;
  CharMatrixSearchService searcher;

  private class DummyCharMatrix extends BaseDummyCharMatrix {
    private final String [][][] data = { { {"aaa", "aba"} } };

    public String getKey(int row, int col) throws IllegalArgumentException {
      return data[row][col][0];
    }

    public String getValue(int row, int col) throws IllegalArgumentException {
      return data[row][col][1];
    }
  }

  @Before
  public void before() {
    matrix = new DummyCharMatrix();
    searcher = new CharMatrixSearchService();
  }

  @Test
  public void testGetQueryOccurrencesWithOne() {
    String query = "b";
    int expected = 1;
    int result = searcher.getQueryOccurrencesInValue(matrix, query, 0, 0);

    assertTrue(expected == result);
  }

  @Test
  public void testGetQueryOccurrencesWithMultipleNonContiguous()  {
    String query = "a";
    int expected = 2;
    int result = searcher.getQueryOccurrencesInValue(matrix, query, 0, 0);

    assertTrue(expected == result);
  }

  @Test
  public void testGetQueryOccurrencesWithMultipleContiguous() {
    String query = "aa";
    int expected = 2;
    int result = searcher.getQueryOccurrencesInKey(matrix, query, 0, 0);

    assertTrue(expected == result);
  }
}