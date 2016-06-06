public class AsciiMatrix {
  // ****************************************
  // Constants
  // ****************************************
  static final String ALLOWED_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  // ****************************************
  // Fields
  // ****************************************
  int maxLength;

  String[][] data;
  // ****************************************
  // Initialization
  // ****************************************
  public AsciiMatrix(int rows, int cols) {
    data = new String[rows][cols];

    int defaultLength = 3;

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        data[r][c] = generateRandomAsciiCell(defaultLength);
      }
    }

    maxLength = defaultLength;
  }

  String generateRandomAsciiCell(int len) {
    StringBuilder sb = new StringBuilder(len);

    for(int i = 0; i < len; i++) {
      int rand = (int)(Math.random() * ALLOWED_CHARS.length());
      sb.append(ALLOWED_CHARS.charAt(rand));
    }

    return sb.toString();
  }
  // ****************************************
  // Accessors
  // ****************************************
  public String get(int row, int col) {
    if (row < data.length) {
      if (col < data[row].length) {
        return data[row][col];
      }
    }
    return "";
  }

  public void set(int row, int col, String value) {
    if (row < data.length) {
      if (col < data[row].length) {
        data[row][col] = value;
        if (value.length() > maxLength) {
          maxLength = value.length();
        }
      }
    }
  }
  // ****************************************
  // Count occurrences
  // ****************************************
  int countQueryOccurrencesInCell(int row, int col, String query) {
    int occ = 0;

    String cell = get(row, col);
  
    for (int i = 0; i <= cell.length() - query.length(); i++) {
      if (cell.substring(i, i + query.length()).equals(query)) {
        occ++;
      }
    }

    return occ;
  }

  // ****************************************
  // Representation
  // ****************************************
  public void printToConsole() {
    System.out.println(toString());
  }

  @Override
  public String toString() {
    StringBuilder table = new StringBuilder();

    for (int r = 0; r < data.length; r++) {
      StringBuilder tableRow = new StringBuilder();

      for (int c = 0; c < data[r].length; c++) {
        tableRow.append(String.format("%1$"+ (maxLength + 1) + "s", get(r, c)));
        tableRow.append(" ");
      }

      table.append(tableRow.toString().trim());
      table.append(System.lineSeparator());
    }

    return table.toString().trim();
  }
}