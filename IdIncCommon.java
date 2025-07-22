abstract public class IdIncCommon {
  protected final int tailLen;

  public IdIncCommon(int modifiableTailLength) {
    this.tailLen = modifiableTailLength;
  }

  public String next(String id) throws Exception {
    if (id.length() < tailLen) {
      throw new Exception("id (" + id + ") has length < " + tailLen + ".");
    }

    char[] tail = charTail(id);
    char[] nxtTail = nextTail(tail);
    StringBuffer idCut = new StringBuffer(id.substring(0, (id.length() - tailLen)));

    for (int i = tailLen - 1; i >= 0; i--) {
      idCut.append(nxtTail[i]);
    }

    return idCut.toString();
  }

  private char[] charTail(String id) {
    char[] tail = new char[tailLen];
    int idLen = id.length();

    for (int i = 0; i < tailLen; i++) {
      tail[i] = id.charAt(idLen - i - 1);
    }

    return tail;
  }

  private char[] nextTail(char[] tail) {
    for (int i = 0; i < tailLen; i++) {
      tail[i] = nextChar(tail[i]);

      if (tail[i] != '0') {
        break;
      }
    }

    return tail;
  }

  abstract protected char nextChar(char c);
}