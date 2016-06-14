import java.util.ArrayList;
import java.io.IOException; 

public class AsciiMatrix {
  private ArrayList<AsciiMatrixRow> data;
  private AsciiMatrixInputStrategy inputStrategy;
  private AsciiMatrixOutputStrategy outputStrategy;

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

  public void clear() {
    data.clear();
  }
  
  public void sort() {
    for (AsciiMatrixRow row : data) {
      row.sort();
    }
  }

  public void autoFill(int rows, int cols) throws IllegalArgumentException {
    if (rows < 0 || cols < 0) {
      throw new IllegalArgumentException("Negative number of rows/columns is not allowed.");
    }

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

  public void setInputStrategy(AsciiMatrixInputStrategy strat) {
    inputStrategy = strat;
  }

  public void initializeFromInput() throws IOException {
    inputStrategy.readMatrix(this);
  }

  public void setOutputStrategy(AsciiMatrixOutputStrategy strat) {
    outputStrategy = strat;
  }

  public void outputContents() throws IOException {
    outputStrategy.writeMatrix(this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int index = 0; index < size(); index++) {
      sb.append(get(index));
      if (index < size() - 1) {
        sb.append(System.lineSeparator());
      }
    }

    return sb.toString();
  }
}