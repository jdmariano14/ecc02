public class AsciiMatrixCell implements Comparable<AsciiMatrixCell> {
  public static final int DEFAULT_SIZE = 2;

  private String [] data;

  public AsciiMatrixCell(int elements) throws NegativeArraySizeException {
    data = new String[elements];
  }

  public AsciiMatrixCell() {
    this(DEFAULT_SIZE);
  }

  public int size() {
    return data.length;
  }

  public String get(int index) throws ArrayIndexOutOfBoundsException {
    return data[index];
  }

  public void set(int index, String newVal) throws ArrayIndexOutOfBoundsException {
    data[index] = newVal;
  }

  public static AsciiMatrixCell parseCell(String str) {
    String[] tokens = str.split("\\s*,\\s+");
    AsciiMatrixCell cell = new AsciiMatrixCell(tokens.length);

    for (int index = 0; index < tokens.length; index++) {
      cell.set(index, tokens[index]);
    }

    return cell;
  }

  public String concatenate(String delimiter) {
    StringBuilder sb = new StringBuilder();
    
    for (int index = 0; index < size(); index++) {
      sb.append(get(index));
      if (index < size() - 1) { 
        sb.append(delimiter); 
      }
    }

    return sb.toString();
  }

  public String concatenate() {
    return concatenate("");
  }

  @Override
  public int compareTo(AsciiMatrixCell other) {
    String thisConcat = this.concatenate();
    String otherConcat = other.concatenate();
    
    return thisConcat.compareToIgnoreCase(otherConcat);
  }

  @Override
  public String toString() {
    return concatenate(", ");
  }
}