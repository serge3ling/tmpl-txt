public class IdInc {
  private final int tailLen;

  public IdInc(int modifiableTailLength) {
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

  private char nextChar(char c) {
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