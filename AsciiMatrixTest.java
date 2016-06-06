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

  public void testMaxLengthUpdate() {
    String newVal = m.get(0, 0) + "aaa";
    m.set(0, 0, newVal);
    assertTrue(m.maxLength == newVal.length());
  }

  public void testToString() {
    m.set(0, 0, "a");
    m.set(0, 1, "bb");
    m.set(1, 0, "ccc");
    m.set(1, 1, "dddd");

    String expected = "a    bb" + System.lineSeparator() + "ccc  dddd";
    String result = m.toString();
    
    System.out.println();
    System.out.println();
    System.out.println(expected);
    System.out.println();
    System.out.println();
    System.out.println(result);
    System.out.println();
    System.out.println();
    assertEquals(expected, result);
  }

}