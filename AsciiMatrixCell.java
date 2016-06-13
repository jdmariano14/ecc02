import java.util.HashSet;

public class AsciiMatrixCell implements Comparable<AsciiMatrixCell> {
  public static final int DEFAULT_SIZE = 2;
  public static final int DEFAULT_ELEMENT_LENGTH = 3;
  public static final char DELIMITER = ',';
  public static final HashSet<Character> ILLEGAL_CHARS;

  private String [] data;

  static {
    ILLEGAL_CHARS = new HashSet();
    ILLEGAL_CHARS.add(DELIMITER);
    ILLEGAL_CHARS.add('(');
    ILLEGAL_CHARS.add(')');
  }

  public AsciiMatrixCell(int size) throws NegativeArraySizeException {
    data = new String[size];
    for (int index = 0; index < size; index++) {
      data[index] = generateRandomAsciiCell(DEFAULT_ELEMENT_LENGTH);
    }
  }

  public AsciiMatrixCell() {
    this(DEFAULT_SIZE);
  }

  private String generateRandomAsciiCell(int length) {
    StringBuilder sb = new StringBuilder(length);

    for (int index = 0; index < length; index++) {
      char randomChar;
      do {
        randomChar = (char)(Math.random() * 256);
      } while (ILLEGAL_CHARS.contains(randomChar));

      sb.append(randomChar);
    }

    return sb.toString();
  }

  public static AsciiMatrixCell parseCell(String str) {
    String[] tokens = str.split(String.valueOf(DELIMITER));
    AsciiMatrixCell cell = new AsciiMatrixCell(tokens.length);

    for (int index = 0; index < tokens.length; index++) {
      cell.set(index, tokens[index]);
    }

    return cell;
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
      if (ILLEGAL_CHARS.contains(c)) {
        String msg = "Input string contains an illegal character ('" + c + "'').";
        throw new IllegalArgumentException(msg);
      }
    }

    data[index] = newVal;
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
    return concatenate(String.valueOf(DELIMITER));
  }
}