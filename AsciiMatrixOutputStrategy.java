import java.io.IOException;

public interface AsciiMatrixOutputStrategy {
  public void writeMatrix(AsciiMatrix matrix) throws IOException;
}