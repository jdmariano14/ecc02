import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.rules.ExpectedException;

public class AsciiMatrixCellTest {
  AsciiMatrixCell cell;
  Throwable e;

  @Rule
  public final ExpectedException thrown = ExpectedException.none();
   
  @Before
  public void before(){
    cell = new AsciiMatrixCell();
    e = null;
  }

  @Test
  public void testSizeWithDefaultConstructor() {
    assertTrue(cell.size() == AsciiMatrixCell.DEFAULT_SIZE);
  }

  @Test
  public void testSizeWithManualConstructor() {
    int newSize = 3;
    cell = new AsciiMatrixCell(newSize);
    
    assertTrue(cell.size() == newSize);
  }

  @Test
  public void testSizeManualConstructorWithNegativeSize() {
    thrown.expect(NegativeArraySizeException.class);

    int negativeSize = -1;
    cell = new AsciiMatrixCell(negativeSize);
  }

  @Test
  public void testGetterWithCorrectIndex() {
    String expected = null;
    String result = cell.get(0);
    
    assertEquals(expected, result);
  }

  @Test
  public void testGetterWithOutOfBoundsIndex() {
    thrown.expect(ArrayIndexOutOfBoundsException.class);
    
    int outOfBoundsIndex = cell.size();
    String result = cell.get(outOfBoundsIndex);
  }

  @Test
  public void testSetterWithCorrectIndex() {
    String expected = "newVal";

    cell.set(0, expected);

    String result = cell.get(0);
    
    assertEquals(expected, result);
  }

  @Test
  public void testSetterWithOutOfBoundsIndex() {
    thrown.expect(ArrayIndexOutOfBoundsException.class);
    
    int outOfBoundsIndex = cell.size();
    cell.set(outOfBoundsIndex, "foo");
  }

  @Test
  public void testParseCell() {
    String key = "key";
    String value = "value";
    String parseThis = key + ", " + value;
    cell = AsciiMatrixCell.parseCell(parseThis);

    String expected = value;
    String result = cell.get(1);
    
    assertEquals(expected, result);
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

    String expected = key + ", " + value;
    String result = cell.toString();
    
    assertEquals(expected, result);
  }
}