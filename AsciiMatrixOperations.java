import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AsciiMatrixOperations {
  // ****************************************
  // Main
  // ****************************************
  public static void main(String args[]) {
    AsciiMatrix m = initializeAsciiMatrix();
    System.out.println();
    System.out.println(m);


  }

  // ****************************************
  // Initialize a matrix
  // ****************************************
  private static AsciiMatrix initializeAsciiMatrix() {
    int rows = promptUserForPostiveInt("Enter the number of rows: ");
    int cols = promptUserForPostiveInt("Enter the number of columns: ");
    return new AsciiMatrix(rows, cols);
  }
  // ****************************************
  // Prompts
  // ****************************************
  private static int promptUserForPostiveInt(String promptMsg) {
    System.out.print(promptMsg);

    int input;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    try {
      input = Integer.parseInt(reader.readLine());
      
      if (input < 1) {
        input = 1;
      }
    }
    catch (IOException | NumberFormatException e) {
      input = 1;
    }

    return input;
  }
}