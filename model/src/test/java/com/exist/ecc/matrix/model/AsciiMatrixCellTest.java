package com.exist.ecc.matrix.model;

import java.util.Arrays;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import org.junit.rules.ExpectedException;

public class AsciiMatrixCellTest {
  AsciiMatrixCell cell;

  @Rule
  public final ExpectedException thrown = ExpectedException.none();
   
  @Before
  public void before(){
    cell = new AsciiMatrixCell();
    cell.autoFill();
  }

  @Test
  public void testGetterWithCorrectIndex() {
    String result = cell.get(0);
    
    assertNotNull(result);
  }

  @Test
  public void testGetterWithOutOfBoundsIndex() {
    thrown.expect(IndexOutOfBoundsException.class);
    
    int outOfBoundsIndex = cell.size();
    String result = cell.get(outOfBoundsIndex);
  }

  @Test
  public void testSetterWithCorrectIndex() {
    String newVal = "  new1234Val" + AsciiMatrixConventions.TEXT_ELEMENT_DELIMITER + "   ";

    cell.set(0, newVal);

    String expected = newVal;
    String result = cell.get(0);
    
    assertEquals(expected, result);
  }

  @Test
  public void testSetterWithOutOfBoundsIndex() {
    thrown.expect(IndexOutOfBoundsException.class);
    
    int outOfBoundsIndex = cell.size();
    cell.set(outOfBoundsIndex, "foo");
  }

  @Test
  public void testAdd() {
    String expected = "foo";

    cell.add(expected);

    String result = cell.get(cell.size() - 1);

    assertEquals(expected, result);
  }

  @Test
  public void testClear() {
    cell.autoFill();

    cell.clear();

    assertTrue(cell.size() == 0);
  }

  @Test
  public void testIterator() {
    StringBuilder sb = new StringBuilder();
    int expected = 0;
    
    for (String element : cell) {
      sb.append(element);
      expected += element.length();
    }

    int result = sb.toString().length();

    assertTrue(expected == result);
  }

  @Test
  public void testIsValidWithValid() {
    char legalChar = 'a';

    assertTrue(AsciiMatrixCell.isValid(legalChar));
  }

  @Test
  public void testIsValidWithNonAscii() {
    char illegalChar = 0xFF;

    assertFalse(AsciiMatrixCell.isValid(illegalChar));
  }

  @Test
  public void testGetQueryOccurrencesWithOneInMultipleElements() {
    String query = "a";
    cell.set(0, "b" + query + "b");
    cell.set(1, query + "cc");

    int [] result = cell.getQueryOccurrences(query);
    int [] expected = {1, 1};

    assertTrue(Arrays.equals(result, expected));
  }

  @Test
  public void testGetQueryOccurrencesWithMultipleNonContiguousInFirstElement()  {
    String query = "aa";
    cell.set(0, query + "b" + query);
    cell.set(1, "aca");

    int [] result = cell.getQueryOccurrences(query);
    int [] expected = {2, 0};

    assertTrue(Arrays.equals(result, expected));
  }

  public void testGetQueryOccurrencesWithMultipleContiguousInOtherElement() {
    String letter = "a";
    String query = letter + letter;
    cell.set(0, "foo");
    cell.set(1, letter + letter + letter);

    int [] result = cell.getQueryOccurrences(query);
    int [] expected = {0, 2};

    assertTrue(Arrays.equals(result, expected));
  }


  @Test
  public void testConcatenate() {
    String key = "key";
    String value = "value";
    cell.set(0, key);
    cell.set(1, value);

    String expected = key + value;
    String result = cell.concatenate();
    
    assertEquals(expected, result);
  }

  @Test
  public void testCompareTo() {
    String low = "aaa";
    String high = "ZZZ";

    cell.clear();
    cell.add(low);
    cell.add(high);

    AsciiMatrixCell greater = new AsciiMatrixCell();

    greater.add(high);
    greater.add(low);

    int result = cell.compareTo(greater);

    assertTrue(result < 0);
  }

  @Test
  public void testToString() {
    String key = "key";
    String value = "value";
    cell.set(0, key);
    cell.set(1, value);

    String expected = key + AsciiMatrixConventions.textElementDivider() + value;
    String result = cell.toString();
    
    assertEquals(expected, result);
  }
}