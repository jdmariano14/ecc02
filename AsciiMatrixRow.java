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

  public void add(AsciiMatrixCell cell) {
    data.add(cell);
  }

  public void sort() {
    Collections.sort(data);
  }

  public void autoFill(int cols) {
    for (int ctr = 0; ctr < cols; ctr++) {
      AsciiMatrixCell cell = new AsciiMatrixCell();
      cell.autoFill();
      add(cell);
    }
  }

  public ArrayList<int[]> getQueryOccurrences(String query) {
    ArrayList<int[]> occurrences = new ArrayList(size());
    
    for (AsciiMatrixCell cell : data) {
      occurrences.add(cell.getQueryOccurrences(query));
    }

    return occurrences;
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