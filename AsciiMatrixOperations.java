import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AsciiMatrixOperations {
  // ****************************************
  // Main
  // ****************************************
  public static void main(String args[]) {
    int a = promptUserForPostiveInt("Enter a positive number: ");
    int b = promptUserForPostiveInt("Enter a positive number: ");

    System.out.println(a+b);
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