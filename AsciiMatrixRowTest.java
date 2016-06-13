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
    thrown.expect(ArrayIndexOutOfBoundsException.class);
    
    int outOfBoundsIndex = row.size();
    AsciiMatrixCell result = row.get(outOfBoundsIndex);
  }

}