import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import org.junit.rules.ExpectedException;

public class AsciiMatrixTest {
  AsciiMatrix matrix;

  @Rule
  public final ExpectedException thrown = ExpectedException.none();
   
  @Before
  public void before(){
    matrix = new AsciiMatrix();
  }

  @Test
  public void testSize() {
    assertTrue(matrix.size() == 0);
  }

  @Test
  public void testGetterWithOutOfBoundsIndex() {
    thrown.expect(IndexOutOfBoundsException.class);
    
    int outOfBoundsIndex = matrix.size();
    AsciiMatrixRow result = matrix.get(outOfBoundsIndex);
  }

  @Test
  public void testAdd() {
    AsciiMatrixRow row = new AsciiMatrixRow();
    row.autoFill(1);

    matrix.add(row);

    AsciiMatrixRow expected = row;
    AsciiMatrixRow result = matrix.get(matrix.size() - 1);

    assertEquals(expected, result);
  }

  @Test
  public void testClear() {
    AsciiMatrixRow row = new AsciiMatrixRow();
    row.autoFill(1);

    matrix.add(row);
    matrix.clear();

    assertTrue(matrix.size() == 0);
  }

  @Test
  public void testSort() {
    String firstRowFirstCell = "aa";
    String firstRowSecondCell = "bb";
    String secondRowFirstCell = "cc";
    String secondRowSecondCell = "dd";

    matrix.autoFill(2, 2);
    matrix.get(0).get(0).set(0, firstRowSecondCell);
    matrix.get(0).get(1).set(0, firstRowFirstCell);
    matrix.get(1).get(0).set(0, secondRowSecondCell);
    matrix.get(1).get(1).set(0, secondRowFirstCell);

    matrix.sort();

    String expected1 = firstRowFirstCell;
    String result1 = matrix.get(0).get(0).get(0);
    String expected2 = secondRowFirstCell;
    String result2 = matrix.get(1).get(0).get(0);

    assertEquals(expected1, result1);
    assertEquals(expected2, result2);
  }

  @Test
  public void testAutoFill() {
    matrix.autoFill(1, 1);
    AsciiMatrixRow result = matrix.get(0);
    
    assertNotNull(result);
  }

  @Test
  public void testAutoFillWithNegativeArgument() {
    thrown.expect(IllegalArgumentException.class);
    
    matrix.autoFill(-1, 1);
  }

  @Test
  public void testGetQueryOccurrencesWithOneInMultipleRows() {
    String query = "a";
    matrix.autoFill(3, 3);
    matrix.get(0).get(0).set(0, query);
    matrix.get(1).get(1).set(0, query);
    matrix.get(2).get(2).set(0, query);

    ArrayList<ArrayList<int[]>> result = matrix.getQueryOccurrences(query);

    assertTrue(result.get(0).get(0)[0] > 0);
    assertTrue(result.get(1).get(1)[0] > 0);
    assertTrue(result.get(2).get(2)[0] > 0);
  }
}