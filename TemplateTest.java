public class TemplateTest {
  private final Test test = new Test();

  public void testAll() {
    String testName = "TemplateTest.testAll";
    System.out.println(testName + " started.");

    oneTestFilename("cov.xml", "name=${code}.xml, write=over", "${code}.xml");
    oneTestFilename("cov-lookups.xml", "name=${code}-lookups.xml, write=over", "${code}-lookups.xml");
    oneTestFilename("gc_class_coverage_codes.xml", "write=tail", "gc_class_coverage_codes.xml");
    oneTestFilename("productmodel.display.properties", "write=tail", "productmodel.display.properties");
    oneTestToWriteTail("cov.xml", "name=${code}.xml, write=over", false);
    oneTestToWriteTail("cov-lookups.xml", "name=${code}-lookups.xml, write=over", false);
    oneTestToWriteTail("gc_class_coverage_codes.xml", "write=tail", true);
    oneTestToWriteTail("productmodel.display.properties", "write=tail", true);

    System.out.println(testName + " done.");
  }

  private void oneTestFilename(String key, String val, String expect) {
    Template tmpl = new Template(key, val);
    test.show(
      "key: \"" + key + "\", val: \"" + val + "\", filename()",
      tmpl.filename().equals(expect)
    );
  }

  private void oneTestToWriteTail(String key, String val, boolean expect) {
    Template tmpl = new Template(key, val);
    test.show(
      "key: \"" + key + "\", val: \"" + val + "\", toWriteTail()",
      tmpl.toWriteTail() == expect
    );
  }
}