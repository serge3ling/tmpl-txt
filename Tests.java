public class Tests {
  public static void main(String[] args) throws Exception {
    boolean withFileReadWrite = true;

    ReplaceTest replaceTest = new ReplaceTest();
    replaceTest.testAll();

    IdIncTest idIncTest = new IdIncTest();
    idIncTest.testAll();

    TemplateTest templateTest = new TemplateTest();
    templateTest.testAll();

    TemplatesTest templatesTest = new TemplatesTest();
    templatesTest.testAll();

    try {
      LinesWrapTest linesTest = new LinesWrapTest();
      linesTest.testAll();
    } catch (Exception ex) {
      System.out.println("Exception in LinesTest.testAll: " + ex.getLocalizedMessage());
      ex.printStackTrace();
    }

    if (withFileReadWrite) {
      try {
        FileLines fileLines = new FileLines("tags2vals.txt");
        java.util.List<String> lines = fileLines.read();
        LinesFile linesFile = new LinesFile("tags2vals-copy.txt");
        linesFile.overwrite(lines);
        linesFile.writeTail(lines);
      } catch (Exception ex) {
        System.out.println("Exception in FileLines.read: " + ex.getLocalizedMessage());
        ex.printStackTrace();
      }

      try {
        Tags2Vals t2v = new Tags2Vals();
        java.util.List<java.util.Map<String, String>> maps = t2v.maps();
        System.out.println("maps are " + maps.size());
      } catch (Exception ex) {
        System.out.println("Exception in Tags2Vals.maps: " + ex.getLocalizedMessage());
        ex.printStackTrace();
      }
    }
  }
}