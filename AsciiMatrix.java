import java.util.ArrayList;

public class AsciiMatrix {
  private ArrayList<AsciiMatrixRow> data;

  public AsciiMatrix() {
    data = new ArrayList();
  }

  public int size() {
    return data.size();
  }

  public AsciiMatrixRow get(int index) throws IndexOutOfBoundsException {
    return data.get(index);
  }

  public void add(AsciiMatrixRow row) {
    data.add(row);
  }

  public void sort() {
    for (AsciiMatrixRow row : data) {
      row.sort();
    }
  }

  public void autoFill(int rows, int cols) {
    for (int ctr = 0; ctr < rows; ctr++) {
      AsciiMatrixRow row = new AsciiMatrixRow();
      row.autoFill(cols);
      add(row);
    }
  }

  public ArrayList<ArrayList<int[]>> getQueryOccurrences(String query) {
    ArrayList<ArrayList<int[]>> occurrences = new ArrayList(size());

    for (AsciiMatrixRow row : data) {
      occurrences.add(row.getQueryOccurrences(query));
    }

    return occurrences;
  }
}