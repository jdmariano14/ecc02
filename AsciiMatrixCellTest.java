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
  }

  @Test
  public void testSizeWithDefaultConstructor() {
    assertTrue(cell.size() == AsciiMatrixConventions.getCellSize());
  }

  @Test
  public void testSizeWithManualConstructor() {
    int newSize = 3;
    cell = new AsciiMatrixCell(newSize);
    
    assertTrue(cell.size() == newSize);
  }

  @Test
  public void testManualConstructorWithNegativeSize() {
    thrown.expect(IllegalArgumentException.class);

    int negativeSize = -1;
    cell = new AsciiMatrixCell(negativeSize);
  }

  @Test
  public void testGetterWithCorrectIndex() {
    cell = new AsciiMatrixCell();
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
    String newVal = "  newVal  ";

    cell.set(0, newVal);

    String expected = newVal.trim();
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
  public void testIsValidWithWhitespace() {
    char illegalChar = ' ';

    assertFalse(AsciiMatrixCell.isValid(illegalChar));
  }

  @Test
  public void testIsValidWithDelimiter() {
    char illegalChar = AsciiMatrixConventions.TEXT_ELEMENT_DELIMITER;

    assertFalse(AsciiMatrixCell.isValid(illegalChar));
  }

  @Test
  public void testAutoFill() {
    cell = new AsciiMatrixCell();
    cell.autoFill();
    String result = cell.get(0);
    
    assertNotNull(result);
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

    cell.set(0, low);
    cell.set(1, high);

    AsciiMatrixCell greater = new AsciiMatrixCell(cell.size());

    greater.set(0, high);
    greater.set(1, low);

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