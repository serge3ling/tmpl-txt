import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OverallWriter {
  public static final String TEMPLATE_DIR = "templates";
  private final String dir;
  private final Tags2Vals tags2vals;
  private final IdGet idGet;
  private final ReplaceCommon replace;
  private List<Map<String, String>> tagMaps = new ArrayList<>();

  public OverallWriter(String dir) {
    this(dir, new Tags2Vals(), new IdGetFile(), new Replace());
  }

  public OverallWriter(String dir, Tags2Vals tags2vals, IdGet idGet, ReplaceCommon replace) {
    this.dir = dir;
    this.tags2vals = tags2vals;
    this.idGet = idGet;
    this.replace = replace;
  }

  public void write() throws Exception {
    tagMaps = tags2vals.maps();

    for (Map<String, String> tagMap : tagMaps) {
      Line lineWrap = new Line(idGet, replace, tagMap);

      FileLines covFileLines = new FileLines(TEMPLATE_DIR + "/cov.xml");
      LinesWrap covLinesWrap = new LinesWrap(lineWrap, covFileLines.read());
      List<String> covRemade = covLinesWrap.remake();
      LinesFile covLinesFile = new LinesFile(dir + "/" + tagMap.get("code") + ".xml");
      covLinesFile.overwrite(covRemade);

      FileLines lkpFileLines = new FileLines(TEMPLATE_DIR + "/cov-lookups.xml");
      LinesWrap lkpLinesWrap = new LinesWrap(lineWrap, lkpFileLines.read());
      List<String> lkpRemade = lkpLinesWrap.remake();
      LinesFile lkpLinesFile = new LinesFile(dir + "/" + tagMap.get("code") + "-lookups.xml");
      lkpLinesFile.overwrite(lkpRemade);

      FileLines class2CodeFileLines = new FileLines(TEMPLATE_DIR + "/gc_class_coverage_codes.xml");
      LinesWrap class2CodeLinesWrap = new LinesWrap(lineWrap, class2CodeFileLines.read());
      List<String> class2CodeRemade = class2CodeLinesWrap.remake();
      LinesFile class2CodeLinesFile = new LinesFile(dir + "/gc_class_coverage_codes.xml");
      class2CodeLinesFile.writeTail(class2CodeRemade);

      FileLines displayFileLines = new FileLines(TEMPLATE_DIR + "/productmodel.display.properties");
      LinesWrap displayLinesWrap = new LinesWrap(lineWrap, displayFileLines.read());
      List<String> displayRemade = displayLinesWrap.remake();
      LinesFile displayLinesFile = new LinesFile(dir + "/productmodel.display.properties");
      displayLinesFile.writeTail(displayRemade);
    }
  }
}