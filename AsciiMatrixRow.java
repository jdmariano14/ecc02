import java.util.ArrayList;
import java.util.Collections;

public class AsciiMatrixRow {
  private ArrayList<AsciiMatrixCell> data;

  public AsciiMatrixRow(int size) throws IllegalArgumentException {
    data = new ArrayList(size);
    for (int index = 0; index < size; index++) {
      data.add(new AsciiMatrixCell());
    }
  }

  public static AsciiMatrixRow parseRow(String str) {
    str = str.trim();
    String[] tokens = str.split("\\s*" + AsciiMatrixCell.CELL_DELIMITER + "\\s*");
    AsciiMatrixRow row = new AsciiMatrixRow(tokens.length);

    for (String token : tokens) {
      row.add(AsciiMatrixCell.parseCell(token));
    }

    return row;
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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    for (int index = 0; index < size(); index++) {
      sb.append(get(index));
      if (index < size() - 1) {
        sb.append(" " + AsciiMatrixCell.CELL_DELIMITER + " ");
      }
    }

    return sb.toString();
  }
}