import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AsciiMatrixOperations {

  public static void main(String [] args) {
    AsciiMatrix matrix = new AsciiMatrix();
    matrix.autoFill(3, 3);
    matrix.setOutputStrategy(new FileOutputStrategy("poo"));
    matrix.outputContents();

    AsciiMatrix natrix = new AsciiMatrix(new FileInputStrategy("foo"));
    natrix.setOutputStrategy(new ConsoleOutputStrategy());
    natrix.outputContents();

  }

/*
  // ****************************************
  // Main
  // ****************************************
  public static void main(String args[]) {
    AsciiMatrix matrix = initializeAsciiMatrix();

    int choice = 0;
    do {
      System.out.println();
      System.out.println(getMenu());

      choice = promptUserForPostiveInt("");

      switch (choice) {
        case 1: 
          searchMatrix(matrix);
          break;
        case 2: 
          editMatrixCell(matrix);
          break;
        case 3: 
          System.out.println(matrix);
          break;
        case 4:
          matrix = initializeAsciiMatrix();
          break; 
      }
    } while (choice != 5);

    System.out.println("Goodbye.");
  }
  // ****************************************
  // Menu
  // ****************************************
  public static String getMenu() {
    StringBuilder menu = new StringBuilder();

    menu.append("Select an option:" + System.lineSeparator());
    menu.append("1. Search" + System.lineSeparator());
    menu.append("2. Edit" + System.lineSeparator());
    menu.append("3. Print" + System.lineSeparator());
    menu.append("4. Reset" + System.lineSeparator());
    menu.append("5. Exit" + System.lineSeparator());

    return menu.toString().trim();
  }
  // ****************************************
  // Search matrix
  // ****************************************
  private static void searchMatrix(AsciiMatrix m) {
    String query = promptUserForString("Enter the search query: ");
    System.out.println(m.getSearchResults(query));
  }
  // ****************************************
  // Initialize a matrix
  // ****************************************
  private static AsciiMatrix initializeAsciiMatrix() {
    int rows = promptUserForPostiveInt("Enter the number of rows: ");
    int cols = promptUserForPostiveInt("Enter the number of columns: ");

    AsciiMatrix matrix = new AsciiMatrix(rows, cols);

    System.out.println();
    System.out.println(matrix);

    return matrix;
  }
  // ****************************************
  // Edit a cell
  // ****************************************
  private static void editMatrixCell(AsciiMatrix m) {
    int row = promptUserForNonNegativeInt("Enter row (first is 0): ");
    int col = promptUserForNonNegativeInt("Enter column (first is 0): ");
    String newVal = promptUserForString("Enter the new value: ");

    m.set(row, col, newVal);
  }
  // ****************************************
  // Prompts
  // ****************************************
  private static String promptUserForString(String promptMsg) {
    System.out.print(promptMsg);

    String input;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    try {
      input = reader.readLine();
    }
    catch (IOException e) {
      input = "";
    }

    return input;
  }

  private static int promptUserForNonNegativeInt(String promptMsg) {
    return promptUserForIntWithLowerBound(promptMsg, 0);
  }

  private static int promptUserForPostiveInt(String promptMsg) {
    return promptUserForIntWithLowerBound(promptMsg, 1);
  }

  private static int promptUserForIntWithLowerBound(String promptMsg, int bound) {    
    System.out.print(promptMsg);

    int input;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    try {
      input = Integer.parseInt(reader.readLine());
      
      if (input < bound) {
        input = bound;
      }
    }
    catch (IOException | NumberFormatException e) {
      input = bound;
    }

    return input;
  }*/
}