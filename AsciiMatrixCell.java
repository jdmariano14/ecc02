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
    newVal = newVal.trim();
    for (char c : newVal.toCharArray()) {
      if (!isValid(c)) {
        String msg = "Input string contains an illegal character ('" + c + "').";
        throw new IllegalArgumentException(msg);
      }
    }

    data[index] = newVal;
  }

  public void autoFill() {
    for (int index = 0; index < size(); index++) {
      data[index] = generateRandomCell(AsciiMatrixConventions.DEFAULT_ELEMENT_LENGTH);
    }
  }

  private String generateRandomCell(int length) {
    StringBuilder sb = new StringBuilder(length);

    for (int index = 0; index < length; index++) {
      char randomChar;
      do {
        randomChar = AsciiMatrixConventions.getRandomChar();
      } while (AsciiMatrixConventions.isIllegal(randomChar));

      sb.append(randomChar);
    }

    return sb.toString();
  }

  public static boolean isValid(char c) {
    return !AsciiMatrixConventions.isIllegal(c);
  }


  public int[] getQueryOccurrences(String query) {
    int[] occurrences = new int[size()];

    for (int index = 0; index < size(); index++) {
      String element = get(index);
      for (int pos = 0; pos <= element.length() - query.length(); pos++) {
        String substr = element.substring(pos, pos + query.length());
        if (substr.equals(query)) {
          occurrences[index]++;
        }
      }
    }

    return occurrences;
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
    return concatenate(AsciiMatrixConventions.textElementDivider());
  }
}