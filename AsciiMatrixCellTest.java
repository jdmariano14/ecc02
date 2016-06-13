import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.rules.ExpectedException;

public class AsciiMatrixCellTest {
  AsciiMatrixCell cell;

  @Rule
  public final ExpectedException thrown = ExpectedException.none();
   
  @Before
  public void before(){
    cell = new AsciiMatrixCell(false);
  }

  @Test
  public void testSizeWithDefaultConstructor() {
    assertTrue(cell.size() == AsciiMatrixCell.DEFAULT_SIZE);
  }

  @Test
  public void testSizeWithManualConstructor() {
    int newSize = 3;
    cell = new AsciiMatrixCell(newSize, false);
    
    assertTrue(cell.size() == newSize);
  }

  @Test
  public void testManualConstructorWithNegativeSize() {
    thrown.expect(NegativeArraySizeException.class);

    int negativeSize = -1;
    cell = new AsciiMatrixCell(negativeSize, false);
  }

  @Test
  public void testGetterWithCorrectIndex() {
    cell = new AsciiMatrixCell(true);
    String result = cell.get(0);
    
    assertNotNull(result);
  }

  @Test
  public void testGetterWithOutOfBoundsIndex() {
    thrown.expect(ArrayIndexOutOfBoundsException.class);
    
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
  public void testSetterWithIllegalCharacter() {
    thrown.expect(IllegalArgumentException.class);
    char illegalChar = AsciiMatrixCell.ELEMENT_DELIMITER;

    cell.set(0, "foo" + illegalChar);
  }

  @Test
  public void testSetterWithOutOfBoundsIndex() {
    thrown.expect(ArrayIndexOutOfBoundsException.class);
    
    int outOfBoundsIndex = cell.size();
    cell.set(outOfBoundsIndex, "foo");
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

    AsciiMatrixCell greater = new AsciiMatrixCell(cell.size(), false);

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

    String expected = key + AsciiMatrixCell.ELEMENT_DELIMITER + " " + value;
    String result = cell.toString();
    
    assertEquals(expected, result);
  }
}