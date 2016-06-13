import java.util.ArrayList;

public class AsciiMatrixRow {
  private ArrayList<AsciiMatrixCell> data;

  public AsciiMatrixRow(int size) throws IllegalArgumentException {
    data = new ArrayList(size);
    for (int index = 0; index < size; index++) {
      data.add(new AsciiMatrixCell());
    }
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
}