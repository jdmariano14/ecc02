package com.exist.ecc.matrix.model.api;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public abstract class CharMatrixLogicTest {
  protected CharMatrix matrix;

  protected class DummyCharDomain implements CharDomain {
    public boolean contains(char c) {
      return true;
    }
  }

  protected CharDomain getDefaultCharDomain() {
    return new DummyCharDomain();
  }

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  @Test
  public void testSetSource() {
    Path expected = Paths.get(".");
    Path result;

    matrix.setSource(expected);

    result = matrix.getSource();

    assertEquals(expected, result);
  }

  @Test
  public void testAddRow() {
    int expected = matrix.rows() + 1;
    int result;

    matrix.addRow();

    result = matrix.rows();

    assertTrue(expected == result);
  }

  @Test
  public void testPutKey() {
    String expected = "key";
    String result;

    matrix.addRow();
    matrix.put(0, expected, "value");

    result = matrix.getKey(0, matrix.cols(0) - 1);

    assertEquals(expected, result);
  }

  @Test
  public void testPutValue() {
    String expected = "value";
    String result;

    matrix.addRow();
    matrix.put(0, "key", expected);

    result = matrix.getValue(0, matrix.cols(0) - 1);

    assertEquals(expected, result);
  }

  @Test
  public void testSetKey() {
    String expected = "newKey";
    String result;

    matrix.addRow();
    matrix.put(0, "oldKey", "value");
    matrix.setKey(0, matrix.cols(0) - 1, expected);

    result = matrix.getKey(0, matrix.cols(0) - 1);

    assertEquals(expected, result);
  }

  @Test
  public void testSetValue() {
    String expected = "newValue";
    String result;

    matrix.addRow();
    matrix.put(0, "key", "oldValue");
    matrix.setValue(0, matrix.cols(0) - 1, expected);

    result = matrix.getValue(0, matrix.cols(0) - 1);

    assertEquals(expected, result);
  }

  @Test
  public void testSortRow() {
    String expected = "abc";
    String unexpected = "def";
    String result;

    matrix.addRow();
    matrix.put(0, unexpected, expected);
    matrix.put(0, expected, unexpected);
    matrix.sortRow(0);

    result = matrix.getKey(0, 0);

    assertEquals(expected, result); 
  }

  @Test
  public void testSortRowDescending() {
    String expected = "def";
    String unexpected = "aba";
    String result;

    matrix.addRow();
    matrix.put(0, unexpected, expected);
    matrix.put(0, expected, unexpected);
    matrix.sortRow(0, true);

    result = matrix.getKey(0, 0);

    assertEquals(expected, result);
  }
}