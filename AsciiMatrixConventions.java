import java.util.HashSet;

public class AsciiMatrixConventions {  
  public static final int DEFAULT_CELL_SIZE = 2;
  public static final int DEFAULT_ELEMENT_LENGTH = 3;

  public static final char TEXT_ELEMENT_DELIMITER = ',';
  public static final char TEXT_CELL_DELIMITER = '|';
  
  private static final HashSet<Character> BLACKLIST;

  static {
    BLACKLIST = new HashSet();
    BLACKLIST.add(TEXT_ELEMENT_DELIMITER);
    BLACKLIST.add(TEXT_CELL_DELIMITER);
  }

  public static boolean isIllegal(char c) {
    return BLACKLIST.contains(c);
  }

  public static String textElementDivider() {
    return TEXT_ELEMENT_DELIMITER + " ";
  }

  public static String textCellDivider() {
    return " " + TEXT_CELL_DELIMITER + " ";
  }

}