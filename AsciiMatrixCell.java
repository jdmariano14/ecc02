public class AsciiMatrixCell {
  public static final int DEFAULT_SIZE = 2;

  private String [] data;

  public AsciiMatrixCell(int elements) {
    data = new String[elements];
  }

  public AsciiMatrixCell() {
    this(DEFAULT_SIZE);
  }

  public int size() {
    return data.length;
  }
}