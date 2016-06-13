import java.util.HashSet;

public class AsciiMatrixCell implements Comparable<AsciiMatrixCell> {
  public static final int DEFAULT_SIZE = 2;
  public static final int DEFAULT_ELEMENT_LENGTH = 3;
  public static final HashSet<Character> PROHIBITED_CHARS;

  private String [] data;

  static {
    PROHIBITED_CHARS = new HashSet();
    PROHIBITED_CHARS.add(',');
    PROHIBITED_CHARS.add('(');
    PROHIBITED_CHARS.add(')');
  }

  public AsciiMatrixCell(int elements) throws NegativeArraySizeException {
    data = new String[elements];
    for (int index = 0; index < elements; index++) {
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
      } while (PROHIBITED_CHARS.contains(randomChar));

      sb.append(randomChar);
    }

    return sb.toString();
  }

  public static AsciiMatrixCell parseCell(String str) {
    String[] tokens = str.split("\\s*,\\s+");
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

  public void set(int index, String newVal) throws ArrayIndexOutOfBoundsException {
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
    return concatenate(", ");
  }
}