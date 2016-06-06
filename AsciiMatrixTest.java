import junit.framework.*;

public class AsciiMatrixTest extends TestCase {
  protected AsciiMatrix m;
   
  protected void setUp(){
    m = new AsciiMatrix(2, 2);
  }

  public void testConstructorInitializes2DArrayOfCorrectRowNumber() {
    assertTrue(m.data.length == 2);
  }

  public void testConstructorInitializes2DArrayOfCorrectColNumber() {
    assertTrue(m.data[m.data.length - 1].length == 2);
  }

  public void testGenerateRandomAsciiHasCorrectLength() {
    String result = m.generateRandomAsciiCell(3);
    assertTrue(result.length() == 3);
  }

}