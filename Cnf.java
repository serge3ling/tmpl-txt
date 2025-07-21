import java.util.Map;
import java.util.List;
import java.util.TreeMap;

public class Cnf {
  private final String defaultFilename = "cnf.yml";
  private final String filename;

  private boolean wasRead;
  private final Map<String, String> ymlMap = new TreeMap<>();

  public Cnf() {
    this.filename = this.defaultFilename;
  }

  public Cnf(String filename) {
    this.filename = filename;
  }

  private void read() throws Exception {
    if (wasRead) {
      return;
    }

    wasRead = true;

    FileLines fileLines = new FileLines(this.filename);
    List<String> lines = fileLines.read();

    for (String line : lines) {
      CheckYmlLine checkYmlLine = new CheckYmlLine(line);

      if (checkYmlLine.keyFound()) {
        ymlMap.put(checkYmlLine.key(), checkYmlLine.value());
      }
    }
  }

  public String covSrc() throws Exception {
    return readNeededKey("cov-src");
  }

  public String filenameRegex() throws Exception {
    return readNeededKey("filename-regex");
  }

  public String covTrg() throws Exception {
    return readNeededKey("cov-trg");
  }

  private String readNeededKey(String key) throws Exception {
    read();
    
    if (!ymlMap.containsKey(key)) {
      throw new Exception("File \"" + this.filename + "\" does not have key \"" + key + "\".");
    }

    return ymlMap.get(key);
  }
}