public class AlphaCharDomain extends CharDomain {

  public boolean isInDomain(char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }

}