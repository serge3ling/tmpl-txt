import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TemplatesTest {
  private final Test test = new Test();

  public void testAll() throws Exception {
    String testName = "TemplatesTest.testAll";
    System.out.println(testName + " started.");

    test1();

    System.out.println(testName + " done.");
  }

  void test1() throws Exception {
    String k1 = "cov.xml";
    String k2 = "cov-lookups.xml";
    String k3 = "gc_class_coverage_codes.xml";
    String k4 = "productmodel.display.properties";
    String filename1 = "${code}.xml";
    String v1 = "name=" + filename1 + ", write=over";
    String filename2 = "${code}-lookups.xml";
    String v2 = "name=" + filename2 + ", write=over";
    String v3 = "write=tail";
    String v4 = "write=tail";
    String line1 = k1 + ": " + v1;
    String line2 = k2 + ": " + v2;
    String line3 = k3 + ": " + v3;
    String line4 = k4 + ": " + v4;

    List<String> lines = new ArrayList<>();
    lines.add(k1 + ": " + v1);
    lines.add(k2 + ": " + v2);
    lines.add(k3 + ": " + v3);
    lines.add(k4 + ": " + v4);
    Templates templates = new Templates(new LinesRead.Stub(lines));

    Map<String, Template> map = templates.templateMap();

    test.show(
      "Template filename in line: \"" + line1 + "\"",
      map.get(k1).filename().equals(filename1)
    );

    test.show(
      "Template filename in line: \"" + line2 + "\"",
      map.get(k2).filename().equals(filename2)
    );

    test.show(
      "Template filename in line: \"" + line3 + "\"",
      map.get(k3).filename().equals(k3)
    );

    test.show(
      "Template filename in line: \"" + line4 + "\"",
      map.get(k4).filename().equals(k4)
    );
  }
}