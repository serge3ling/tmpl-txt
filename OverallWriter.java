import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OverallWriter {
  public static final String TEMPLATE_DIR = "templates";
  private final String dir;
  private final Tags2Vals tags2vals;
  private final List<IdGet> idGets;
  private final ReplaceCommon replace;
  private List<Map<String, String>> tagMaps = new ArrayList<>();
  private Templates templateWrap = new Templates();

  public OverallWriter(String dir) {
    this(dir, new Tags2Vals(), new ArrayList<IdGet>(), new Replace());
  }

  public OverallWriter(String dir, List<IdGet> idGets) {
    this(dir, new Tags2Vals(), idGets, new Replace());
  }

  public OverallWriter(String dir, Tags2Vals tags2vals, List<IdGet> idGets, ReplaceCommon replace) {
    this.idGets = idGets;

    if (idGets.size() == 0) {
      this.idGets.add(new IdGetFile());
    }

    this.dir = dir;
    this.tags2vals = tags2vals;
    this.replace = replace;
  }

  public void write() throws Exception {
    tagMaps = tags2vals.maps();
    Map<String, Template> templates = templateWrap.templateMap();
    Set<String> templatesKeySet = templates.keySet();

    for (Map<String, String> tagMap : tagMaps) {
      Line lineWrap = new Line(idGets, replace, tagMap);

      for (String key : templatesKeySet) {
        FileLines fileLines = new FileLines(TEMPLATE_DIR + "/" + key);
        LinesWrap linesWrap = new LinesWrap(lineWrap, fileLines.read());
        List<String> remadeLines = linesWrap.remake();
        String remadeFilename = lineWrap.replaceInOneLine(templates.get(key).filename());
        LinesFile linesFile = new LinesFile(dir + "/" + remadeFilename);

        if (templates.get(key).toWriteTail()) {
          linesFile.writeTail(remadeLines);
        } else {
          linesFile.overwrite(remadeLines);
        }
      }
    }
  }
}