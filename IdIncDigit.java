public class IdIncDigit extends IdIncCommon {
  public IdIncDigit(int modifiableTailLength) {
    super(modifiableTailLength);
  }

  @Override
  protected char nextChar(char c) {
    char rtn = (char)(c + 1);

    if (c == '9') {
      rtn = '0';
    }

    return rtn;
  }
}