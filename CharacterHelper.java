public class CharacterHelper {

  public static boolean isAscii(char c) {
    return (String.valueOf(c)).matches("\\A\\p{ASCII}*\\z");
  }

  public static boolean isWhitespace(char c) {
    return (String.valueOf(c)).matches("\\A\\s+\\z");
  }

}