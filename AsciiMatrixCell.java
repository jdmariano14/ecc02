public class AsciiMatrixCell implements Comparable<AsciiMatrixCell> {
  private String [] data;

  public AsciiMatrixCell(int size) throws NegativeArraySizeException {
    data = new String[size];
  }

  public AsciiMatrixCell() {
    this(AsciiMatrixConventions.DEFAULT_CELL_SIZE);
  }

  public int size() {
    return data.length;
  }

  public String get(int index) throws ArrayIndexOutOfBoundsException {
    return data[index];
  }

  public void set(int index, String newVal) 
      throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
    for (char c : newVal.toCharArray()) {
      if (AsciiMatrixConventions.isIllegal(c)) {
        String msg = "Input string contains an illegal character ('" + c + "').";
        throw new IllegalArgumentException(msg);
      }
    }

    data[index] = newVal.trim();
  }

  protected void autoFill() {
    for (int index = 0; index < size(); index++) {
      data[index] = generateRandomAsciiCell(AsciiMatrixConventions.DEFAULT_ELEMENT_LENGTH);
    }
  }

  private String generateRandomAsciiCell(int length) {
    StringBuilder sb = new StringBuilder(length);

    for (int index = 0; index < length; index++) {
      char randomChar;
      do {
        randomChar = (char)(Math.random() * 256);
      } while (AsciiMatrixConventions.isIllegal(randomChar));

      sb.append(randomChar);
    }

    return sb.toString();
  }

  private String concatenate(String delimiter) {
    StringBuilder sb = new StringBuilder();
    
    for (int index = 0; index < size(); index++) {
      sb.append(get(index));
      if (index < size() - 1) { 
        sb.append(delimiter); 
      }
    }

    return sb.toString();
  }

  protected String concatenate() {
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
    return concatenate(AsciiMatrixConventions.textElementDivider());
  }
}