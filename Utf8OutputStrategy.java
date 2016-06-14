import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.io.BufferedWriter;

public class Utf8OutputStrategy implements AsciiMatrixOutputStrategy {
  private String filepath;

  public Utf8OutputStrategy(String filepath) {
    this.filepath = filepath;
  }

  public void writeMatrix(AsciiMatrix matrix) throws IOException{
    Path path = Paths.get(filepath);

    BufferedWriter writer = Files.newBufferedWriter(path, 
      StandardCharsets.UTF_8, 
      StandardOpenOption.TRUNCATE_EXISTING
    );
    String matrixString = matrix.toString();
    writer.write(matrixString, 0, matrixString.length());
    writer.close();
  }
}