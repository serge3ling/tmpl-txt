public class IdInc extends IdIncCommon {
  public IdInc(int modifiableTailLength) {
    super(modifiableTailLength);
  }

  @Override
  protected char nextChar(char c) {
    char rtn = (char)(c + 1);

    if (c == '9') {
      rtn = 'a';
    }

    if (c == 'z') {
      rtn = '0';
    }

    return rtn;
  }
}