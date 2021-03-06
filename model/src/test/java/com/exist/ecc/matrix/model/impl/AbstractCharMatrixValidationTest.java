package com.exist.ecc.matrix.model.impl;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import com.exist.ecc.matrix.model.api.CharMatrix;
import com.exist.ecc.matrix.model.api.CharDomain;
import com.exist.ecc.matrix.model.api.BaseDummyCharDomain;

public class AbstractCharMatrixValidationTest {
  private static final int ROWS = 1;
  private static final int COLS = 1;
  private static final char LEGAL_CHAR = 'a';
  private static final String LEGAL_CONTENT = String.valueOf(LEGAL_CHAR);

  AbstractCharMatrix matrix;

  private class DummyAbstractCharMatrix extends BaseDummyAbstractCharMatrix {
    public DummyAbstractCharMatrix(CharDomain domain) {
      super(domain);
    }

    public int rows() {
       return AbstractCharMatrixValidationTest.ROWS;
    }

    protected int colsImplementation(int row) { 
      return AbstractCharMatrixValidationTest.COLS; 
    }    
  }

  private class DummyCharDomain extends BaseDummyCharDomain {
    public boolean contains(char c) {
      return c == AbstractCharMatrixValidationTest.LEGAL_CHAR;
    }
  }

  @Rule
  public final ExpectedException thrown = ExpectedException.none();
   
  @Before
  public void before() {
    matrix = new DummyAbstractCharMatrix(new DummyCharDomain());
  }

  @Test
  public void testGetKeyWithValidArguments() {
    matrix.getKey(ROWS - 1, COLS - 1);
  }

  @Test
  public void testGetKeyWithOutOfBoundsRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("out of bounds");
    matrix.getKey(ROWS, COLS - 1);
  }

  @Test
  public void testGetKeyWithNegativeRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("negative");
    matrix.getKey(-1, COLS - 1);
  }

  @Test
  public void testGetKeyWithOutOfBoundsColIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("column");
    thrown.expectMessage("out of bounds");
    matrix.getKey(ROWS - 1, COLS);
  }

  @Test
  public void testGetKeyWithNegativeColIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("column");
    thrown.expectMessage("negative");
    matrix.getKey(ROWS - 1, -1);
  }

  @Test
  public void testSetKeyWithValidArguments() {
    matrix.setKey(ROWS - 1, COLS - 1, LEGAL_CONTENT);
  }

  @Test
  public void testSetKeyWithOutOfBoundsRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("out of bounds");
    matrix.setKey(ROWS, COLS - 1, LEGAL_CONTENT);
  }

  @Test
  public void testSetKeyWithNegativeRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("negative");
    matrix.setKey(-1, COLS - 1, LEGAL_CONTENT);
  }

  @Test
  public void testSetKeyWithOutOfBoundsColIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("column");
    thrown.expectMessage("out of bounds");
    matrix.setKey(ROWS - 1, COLS, LEGAL_CONTENT);
  }

  @Test
  public void testSetKeyWithNegativeColIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("column");
    thrown.expectMessage("negative");
    matrix.setKey(ROWS - 1, -1, LEGAL_CONTENT);
  }

  @Test
  public void testSetKeyWithIllegalContent() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("character");
    matrix.setKey(ROWS - 1, COLS - 1, "kalaberber");
  }

  @Test
  public void testGetValueWithValidArguments() {
    matrix.getValue(ROWS - 1, COLS - 1);
  }

  @Test
  public void testGetValueWithOutOfBoundsRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("out of bounds");
    matrix.getValue(ROWS, COLS - 1);
  }

  @Test
  public void testGetValueWithNegativeRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("negative");
    matrix.getValue(-1, COLS - 1);
  }

  @Test
  public void testGetValueWithOutOfBoundsColIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("column");
    thrown.expectMessage("out of bounds");
    matrix.getValue(ROWS - 1, COLS);
  }

  @Test
  public void testGetValueWithNegativeColIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("column");
    thrown.expectMessage("negative");
    matrix.getValue(ROWS - 1, -1);
  }

  @Test
  public void testSetValueWithValidArguments() {
    matrix.setValue(ROWS - 1, COLS - 1, LEGAL_CONTENT);
  }

  @Test
  public void testSetValueWithOutOfBoundsRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("out of bounds");
    matrix.setValue(ROWS, COLS - 1, LEGAL_CONTENT);
  }

  @Test
  public void testSetValueWithNegativeRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("negative");
    matrix.setValue(-1, COLS - 1, LEGAL_CONTENT);
  }

  @Test
  public void testSetValueWithOutOfBoundsColIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("column");
    thrown.expectMessage("out of bounds");
    matrix.setValue(ROWS - 1, COLS, LEGAL_CONTENT);
  }

  @Test
  public void testSetValueWithNegativeColIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("column");
    thrown.expectMessage("negative");
    matrix.setValue(ROWS - 1, -1, LEGAL_CONTENT);
  }

  @Test
  public void testSetValueWithIllegalContent() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("character");
    matrix.setValue(ROWS - 1, COLS - 1, "kalaberber");
  }

  @Test
  public void testPutWithValidArguments() {
    matrix.put(ROWS - 1, LEGAL_CONTENT, LEGAL_CONTENT);
  }

  @Test
  public void testPutWithOutOfBoundsRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("out of bounds");
    matrix.put(ROWS, LEGAL_CONTENT, LEGAL_CONTENT);
  }

  @Test
  public void testPutWithNegativeRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("negative");
    matrix.put(-1, LEGAL_CONTENT, LEGAL_CONTENT);
  }

  @Test
  public void testPutWithIllegalKey() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("character");
    matrix.put(ROWS - 1, "kalaberber", LEGAL_CONTENT);
  }

  @Test
  public void testPutWithIllegalValue() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("character");
    matrix.put(ROWS - 1, LEGAL_CONTENT, "kalaberber");
  }

  @Test
  public void testPutWithMultipleErrors() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("character");
    matrix.put(ROWS, "kalaberber", "kalaberber");
  }

  @Test
  public void testColsWithValidArguments() {
    matrix.cols(ROWS - 1);
  }

  @Test
  public void testColsWithOutOfBoundsRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("out of bounds");
    matrix.cols(ROWS);
  }

  @Test
  public void testColsWithNegativeRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("negative");
    matrix.cols(-1);
  }

  @Test
  public void testSortRowWithValidArguments() {
    matrix.sortRow(ROWS - 1);
  }

  @Test
  public void testSortRowWithOutOfBoundsRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("out of bounds");
    matrix.sortRow(ROWS);
  }

  @Test
  public void testSortRowWithNegativeRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("row");
    thrown.expectMessage("negative");
    matrix.sortRow(-1);
  }
}