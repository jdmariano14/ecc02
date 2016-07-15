package com.exist.ecc.matrix.service;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

import com.exist.ecc.matrix.model.api.CharMatrix;
import com.exist.ecc.matrix.model.api.CharDomain;

import com.exist.ecc.matrix.model.api.BaseDummyCharMatrix;
import com.exist.ecc.matrix.model.api.BaseDummyCharDomain;

public class CharMatrixPopulateServiceTest {
  CharMatrix matrix;
  CharMatrixPopulateService populator;

  private class DummyCharMatrix extends BaseDummyCharMatrix {
    List<List<List<String>>> data;
    CharDomain domain;

    public DummyCharMatrix() {
      data = new ArrayList();
      domain = new BaseDummyCharDomain();
    }

    public CharDomain getDomain() {
      return domain;
    }

    public void clear() {
      data.clear();
    }

    public void addRow() {
      data.add(new ArrayList());
    }

    public void put(int row, String key, String value) throws IllegalArgumentException {
      List<String> cell = new ArrayList();
      cell.add(key);
      cell.add(value);
      data.get(row).add(cell);
    }

    public String getKey(int row, int col) throws IllegalArgumentException {
      return data.get(row).get(col).get(0);
    }

    public String getValue(int row, int col) throws IllegalArgumentException {
      return data.get(row).get(col).get(1);
    }

    public int rows() {
      return data.size();
    }

    public int cols(int row) throws IllegalArgumentException {
      return data.get(row).size();
    }
  }

  @Before
  public void before() {
    matrix = new DummyCharMatrix();
    populator = new CharMatrixPopulateService();
  }

  @Test
  public void testPopulateGeneratesCorrectNumberOfRows() {
    int expected = 3;
    int result;

    populator.populate(matrix, expected, 1, 1);

    result = matrix.rows();

    assertTrue(expected == result);
  }

  @Test
  public void testPopulateGeneratesCorrectNumberOfColumns()  {
    int expected = 3;
    int result;

    populator.populate(matrix, 1, expected, 1);

    result = matrix.cols(0);

    assertTrue(expected == result);
  }

  @Test
  public void testPopulateGeneratesCorrectElementLength() {
    int expected = 3;
    int result;

    populator.populate(matrix, 1, 1, expected);

    result = matrix.getKey(0,0).length();

    assertTrue(expected == result);
  }

  @Test
  public void testPopulateResetsExistingMatrix() {
    int expected = 1;
    int result;

    for (int i = 0; i < expected + 1; i++) {
      matrix.addRow();
    }

    populator.populate(matrix, expected, expected, expected);

    result = matrix.rows();

    assertTrue(expected == result);
  }
}