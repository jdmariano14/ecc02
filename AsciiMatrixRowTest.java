import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import org.junit.rules.ExpectedException;

public class AsciiMatrixRowTest {
  AsciiMatrixRow row;

  @Rule
  public final ExpectedException thrown = ExpectedException.none();
   
  @Before
  public void before(){
    row = new AsciiMatrixRow();
  }

  @Test
  public void testSize() {
    assertTrue(row.size() == 0);
  }

  @Test
  public void testGetterWithOutOfBoundsIndex() {
    thrown.expect(IndexOutOfBoundsException.class);
    
    int outOfBoundsIndex = row.size();
    AsciiMatrixCell result = row.get(outOfBoundsIndex);
  }

  @Test
  public void testAdd() {
    String key = "abc";
    String value = "def";
    AsciiMatrixCell cell = new AsciiMatrixCell();
    row.add(cell);

    AsciiMatrixCell expected = cell;
    AsciiMatrixCell result = row.get(row.size() - 1);

    assertEquals(expected, result);
  }

  @Test
  public void testAutoFill() {
    row.autoFill(1);
    AsciiMatrixCell result = row.get(0);
    
    assertNotNull(result);
  }

  @Test
  public void testSort() {
    String firstKey = "aa";
    String firstValue = "bb";
    String secondKey = "cc";
    String secondValue = "dd";

    AsciiMatrixCell firstCell = new AsciiMatrixCell(2);
    AsciiMatrixCell secondCell = new AsciiMatrixCell(2);
    firstCell.set(0, firstKey);
    firstCell.set(1, firstValue);
    secondCell.set(0, secondKey);
    secondCell.set(1, secondValue);
    
    row.add(secondCell);
    row.add(firstCell);
    row.sort();

    AsciiMatrixCell expected = firstCell;
    AsciiMatrixCell result = row.get(0);

    assertEquals(expected, result);
  }

  @Test
  public void testToString() {
    row.autoFill(2);

    StringBuilder sb = new StringBuilder();
    sb.append(row.get(0));
    sb.append(AsciiMatrixConventions.textCellDivider());
    sb.append(row.get(1));

    String expected = sb.toString();
    String result = row.toString();
    
    assertEquals(expected, result);
  }
}