import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class AsciiMatrixCellTest {
  AsciiMatrixCell cell;
   
  @Before
  public void before(){
    cell = new AsciiMatrixCell();
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

}