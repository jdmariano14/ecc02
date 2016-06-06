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

  public void testGetter() {
    assertEquals(m.get(0, 0), m.data[0][0]);
  }

  public void testSetter() {
    String newVal = "zxc";
    m.set(0, 0, newVal);
    assertEquals(m.get(0, 0), newVal);

  }

}