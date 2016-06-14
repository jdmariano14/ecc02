import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.BufferedWriter;
import java.io.IOException;

public class Utf8OutputStrategy implements AsciiMatrixOutputStrategy {
  private String filepath;

  public Utf8OutputStrategy(String filepath) {
    this.filepath = filepath;
  }

  public void writeMatrix(AsciiMatrix matrix) {
    Path path = Paths.get(filepath);
    
    try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
      String matrixString = matrix.toString();
      writer.write(matrixString, 0, matrixString.length());
      writer.close();
    }
    catch (IOException e) {
      System.err.println("Error accessing file. Matrix contents were not output.");
    }
  }
}