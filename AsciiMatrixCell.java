public class AsciiMatrixCell {
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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    for (int index = 0; index < size(); index++) {
      sb.append(get(index));
      if (index < size() - 1) { 
        sb.append(", "); 
      }
    }

    return sb.toString();
  }
}