import java.util.ArrayList;
import java.util.Collections;

public class AsciiMatrixRow {
  
  private ArrayList<AsciiMatrixCell> data;

  public AsciiMatrixRow() {
    data = new ArrayList();
  }

  public int size() {
    return data.size();
  }

  public AsciiMatrixCell get(int index) throws IndexOutOfBoundsException {
    return data.get(index);
  }

  protected void autoFill(int count) {
    for (int index = 0; index < count; index++) {
      AsciiMatrixCell cell = new AsciiMatrixCell();
      cell.autoFill();
      add(cell);
    }
  }

  public void add(AsciiMatrixCell cell) {
    data.add(cell);
  }

  public void sort() {
    Collections.sort(data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    for (int index = 0; index < size(); index++) {
      sb.append(get(index));
      if (index < size() - 1) {
        sb.append(AsciiMatrixConventions.textCellDivider());
      }
    }

    return sb.toString();
  }
}