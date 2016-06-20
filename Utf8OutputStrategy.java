import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class Utf8OutputStrategy implements AsciiMatrixOutputStrategy {
  private String filepath;

  public Utf8OutputStrategy(String filepath) {
    this.filepath = filepath;
  }

  public void writeMatrix(AsciiMatrix matrix) throws IOException {
    Path path = Paths.get(filepath);

    BufferedWriter bw = Files.newBufferedWriter(path, 
      StandardCharsets.UTF_8, 
      StandardOpenOption.TRUNCATE_EXISTING
    );
    PrintWriter writer = new PrintWriter(bw);

    writer.println("<matrix>");

    for (AsciiMatrixRow row : matrix) {
      writer.println("<row>");

      for (AsciiMatrixCell cell : row) {
        writer.println("<cell>");

        for (String element : cell) {
          writer.print("<element>");
          writer.print(element);
          writer.println("</element>");
        }

        writer.println("</cell>");
      }

      writer.println("</row>");
    }

    writer.print("</matrix>");

    writer.close();
    bw.close();
  }
}