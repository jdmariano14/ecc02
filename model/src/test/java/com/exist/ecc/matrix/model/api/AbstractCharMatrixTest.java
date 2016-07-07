package com.exist.ecc.matrix.model.api;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import org.junit.rules.ExpectedException;

public class AbstractCharMatrixTest {
  private static final int ROWS = 1;
  private static final int COLS = 1;
  private static final char LEGAL_CHAR = 'a';

  AbstractCharMatrix matrix;

  private class DummyCharMatrix extends AbstractCharMatrix {
    public DummyCharMatrix(CharDomain domain) {
      super(domain);
    }

    public int rows() {
       return AbstractCharMatrixTest.ROWS;
    }

    public int cols() {
       return AbstractCharMatrixTest.COLS;
    }

    public void addRow() {

    }

    public void clear() {

    }
  }

  private class DummyCharDomain implements CharDomain {
    public boolean contains(char c) {
      return c == AbstractCharMatrixTest.LEGAL_CHAR;
    }
  }

  @Rule
  public final ExpectedException thrown = ExpectedException.none();
   
  @Before
  public void before(){
    matrix = new DummyCharMatrix(new DummyCharDomain());
  }

  @Test
  public void testGetKeyWithOutOfBoundsRowIndex() {
    thrown.expect(IllegalArgumentException.class);
    matrix.getKey(ROWS, COLS - 1);
  }
}