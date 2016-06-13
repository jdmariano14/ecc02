import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.rules.ExpectedException;

public class AsciiMatrixRowTest {
  public static final int DEFAULT_SIZE = 2;

  AsciiMatrixRow row;

  @Rule
  public final ExpectedException thrown = ExpectedException.none();
   
  @Before
  public void before(){
    row = new AsciiMatrixRow(DEFAULT_SIZE);
  }

  @Test
  public void testConstructorWithNegativeSize() {
    thrown.expect(IllegalArgumentException.class);

    int negativeSize = -1;
    row = new AsciiMatrixRow(negativeSize);
  }

  @Test
  public void testSize() {
    assertTrue(row.size() == DEFAULT_SIZE);
  }

  @Test
  public void testGetterWithCorrectIndex() {
    AsciiMatrixCell result = row.get(0);
    
    assertNotNull(result);
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
    AsciiMatrixCell cell = AsciiMatrixCell.parseCell(key + "," + value);
    row.add(cell);

    String expected = value;
    String result = row.get(row.size() - 1).get(1);

    assertEquals(expected, result);
  }

  @Test
  public void testToString() {
    StringBuilder sb = new StringBuilder();
    sb.append(row.get(0));
    sb.append(" " + AsciiMatrixCell.CELL_DELIMITER + " ");
    sb.append(row.get(1));

    String expected = sb.toString();
    String result = row.toString();
    
    assertEquals(expected, result);
  }

}