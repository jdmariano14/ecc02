import java.io.IOException;

public interface AsciiMatrixInputStrategy {
  public void readMatrix(AsciiMatrix matrix) throws IOException;
}