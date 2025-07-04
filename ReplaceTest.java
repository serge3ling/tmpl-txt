public class ReplaceTest {
  private final Test test = new Test();

  public void testAll() {
    String testName = "ReplaceTest.testAll";
    System.out.println(testName + " started.");
    Replace r = new Replace();

    test.show(
      "Replace ${name} where there is",
      r.replace(
        "PolicyLine_GCLine.CoveragePattern_${name}.CovTerm_${name}Exc.Description = Excess",
        "name",
        "SJICGLAbusiveAct"
      )
      .equals("PolicyLine_GCLine.CoveragePattern_SJICGLAbusiveAct.CovTerm_SJICGLAbusiveActExc.Description = Excess")
    );

    test.show(
      "Replace ${name} where not there",
      r.replace(
        "abrakadabra",
        "name",
        "SJICGLAbusiveAct"
      )
      .equals("abrakadabra")
    );

    test.show(
      "Replace ${name} where empty",
      r.replace(
        "",
        "name",
        "SJICGLAbusiveAct"
      )
      .equals("")
    );

    System.out.println(testName + " done.");
  }

  public static void main(String[] args) {
    ReplaceTest tst = new ReplaceTest();
    tst.testAll();
  }
}