public class ConsoleOutputStrategy implements AsciiMatrixOutputStrategy {
  public void writeMatrix(AsciiMatrix matrix) {
    System.out.println(matrix);
  }
}