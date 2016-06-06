public class AsciiMatrix {
  // ****************************************
  // Constants
  // ****************************************
  static final String ALLOWED_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  // ****************************************
  // Fields
  // ****************************************
  String[][] data;
  // ****************************************
  // Initialization
  // ****************************************
  public AsciiMatrix(int rows, int cols) {
    data = new String[rows][cols];

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        data[r][c] = generateRandomAsciiCell(3);
      }
    }
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
    return data[row][col];
  }

}