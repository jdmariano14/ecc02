public class AsciiMatrix {

  String[][] data;

  public AsciiMatrix(int rows, int cols) {
    data = new String[rows][cols];
  }

  String generateRandomAsciiCell(int chars) {
    String result = "";

    for (int i = 0; i < chars; i++) {
      char randomChar = (char)(Math.random() * 256);
      result += randomChar;
    }

    return result;
  }

}