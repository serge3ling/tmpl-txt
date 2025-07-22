public class IdIncDigitTest {
  private final Test test = new Test();
  private final IdIncDigit idIncDigit = new IdIncDigit(4);

  public void testAll() {
    String testName = "IdIncDigitTest.testAll";
    System.out.println(testName + " started.");

    testOne("pc:s3:6872679006", "pc:s3:6872679007");
    testOne("pc:s3:6872679009", "pc:s3:6872679010");
    testOne("pc:s3:6872679099", "pc:s3:6872679100");
    testOne("pc:s3:6872679999", "pc:s3:6872670000");

    System.out.println(testName + " done.");
  }

  public void testOne(String id, String expectedNextId) {
    try {
      test.show("Increment " + id, idIncDigit.next(id), expectedNextId);
    } catch (Exception e) {
      test.show("Increment " + id + ": Exception: " + e.getLocalizedMessage(), false);
    }
  }
}