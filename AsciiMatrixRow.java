import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class AsciiMatrixRow implements Iterable<AsciiMatrixCell> {
  
  private List<AsciiMatrixCell> data;

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

  public Iterator<AsciiMatrixCell> iterator() {
    return data.iterator();
  }

  public void sort() {
    Collections.sort(data);
  }

  public void sortDescending() {
    Collections.sort(data, Collections.reverseOrder());
  }

  public void autoFill(int targetSize) throws IllegalArgumentException {
    if (targetSize < 0) {
      throw new IllegalArgumentException("Negative number of columns is not allowed.");
    }

    data.clear();

    for (int ctr = 0; ctr < targetSize; ctr++) {
      AsciiMatrixCell cell = new AsciiMatrixCell();
      cell.autoFill();
      add(cell);
    }
  }

  public List<int[]> getQueryOccurrences(String query) {
    List<int[]> occurrences = new ArrayList(size());
    
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