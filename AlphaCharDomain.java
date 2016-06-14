public class AlphaCharDomain extends CharDomain {

  public boolean isInDomain(char c) {
    return CharacterHelper.isEnglishLetter(c);
  }

}