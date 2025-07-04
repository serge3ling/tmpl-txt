public class Test {
  public void show(String msg, boolean cond) {
    String msgStart = cond ? "[ OK ] " : "[Fail] ";
    System.out.println(msgStart + msg);
  }

  public void show(String msg, Object actual, Object expected) {
    boolean ok = expected.equals(actual);
    String msgStart = ok ? "[ OK ] " : "[Fail] ";
    String msgEnd = ok ? ("") : (". Expected: " + expected + "; actual: " + actual);
    System.out.println(msgStart + msg + msgEnd);
  }
}