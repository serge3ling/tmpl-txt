public class IdIncTest {
  private final Test test = new Test();
  private final IdInc idInc = new IdInc(4);

  public void testAll() {
    String testName = "IdIncTest.testAll";
    System.out.println(testName + " started.");

    testOne("zjmjec7p75mkp8lthhlvar2p2q8", "zjmjec7p75mkp8lthhlvar2p2q9");
    testOne("zjmjec7p75mkp8lthhlvar2p2q9", "zjmjec7p75mkp8lthhlvar2p2qa");
    testOne("zjmjec7p75mkp8lthhlvar2p2qy", "zjmjec7p75mkp8lthhlvar2p2qz");
    testOne("zjmjec7p75mkp8lthhlvar2p2qz", "zjmjec7p75mkp8lthhlvar2p2r0");
    testOne("zjmjec7p75mkp8lthhlvar2p2zz", "zjmjec7p75mkp8lthhlvar2p300");
    testOne("zjmjec7p75mkp8lthhlvar2pzzz", "zjmjec7p75mkp8lthhlvar2q000");
    testOne("zjmjec7p75mkp8lthhlvar2zzzz", "zjmjec7p75mkp8lthhlvar20000");

    System.out.println(testName + " done.");
  }

  public void testOne(String id, String expectedNextId) {
    try {
      test.show("Increment " + id, idInc.next(id), expectedNextId);
    } catch (Exception e) {
      test.show("Increment " + id + ": Exception: " + e.getLocalizedMessage(), false);
    }
  }
}