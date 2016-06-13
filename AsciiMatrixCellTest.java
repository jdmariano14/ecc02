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
  public void testSizeManualConstructorNegativeSize() {
    thrown.expect(NegativeArraySizeException.class);

    int negativeSize = -1;
    cell = new AsciiMatrixCell(negativeSize);
  }



  @Test
  public void testGetWithCorrectIndex() {
    String expected = null;
    String result = cell.get(0);
    
    assertEquals(expected, result);
  }

  @Test
  public void testGetWithOutOfBoundsIndex() {
    thrown.expect(ArrayIndexOutOfBoundsException.class);
    
    int outOfBoundsIndex = cell.size();
    String result = cell.get(outOfBoundsIndex);
  }
}