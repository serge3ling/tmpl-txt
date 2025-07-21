import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Templates {
  private final String defaultFilename = "templates/templates-cnf.yml";
  private final LinesRead linesRead;

  private boolean wasRead;
  private final Map<String, Template> ymlMap = new TreeMap<>();

  public Templates() {
    this.linesRead = new FileLines(this.defaultFilename);
  }

  public Templates(String filename) {
    this.linesRead = new FileLines(filename);
  }

  public Templates(LinesRead linesRead) {
    this.linesRead = linesRead;
  }

  public Map<String, Template> templateMap() throws Exception {
    read();
    return ymlMap;
  }

  private void read() throws Exception {
    if (wasRead) {
      return;
    }

    wasRead = true;

    List<String> lines = linesRead.read();

    for (String line : lines) {
      CheckYmlLine checkYmlLine = new CheckYmlLine(line);

      if (checkYmlLine.keyFound()) {
        var template = new Template(checkYmlLine.key(), checkYmlLine.value());
        ymlMap.put(checkYmlLine.key(), template);
      }
    }
  }
}