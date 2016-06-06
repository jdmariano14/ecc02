import junit.framework.*;

public class AsciiMatrixTest extends TestCase {
  protected AsciiMatrix m;
   
  // assigning the values
  protected void setUp(){
    m = new AsciiMatrix();
  }

  // test method
  public void testGenerateRandomAsciiHasCorrectLength() {
    String result = m.generateRandomAsciiCell(3);
    assertTrue(result.length() == 3);
  }
}