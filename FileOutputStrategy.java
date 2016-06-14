import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.IOException;

public class FileOutputStrategy implements AsciiMatrixOutputStrategy {
  private String filepath;

  public FileOutputStrategy(String filepath) {
    this.filepath = filepath;
  }

  public void writeMatrix(AsciiMatrix matrix) {
    Path path = Paths.get(filepath);
    try (BufferedWriter writer = Files.newBufferedWriter(path)) {
      String matrixString = matrix.toString();
      writer.write(matrixString, 0, matrixString.length());
      writer.close();
    }
    catch (IOException e) {
      System.err.println("Error accessing file.");
    }
  }
}