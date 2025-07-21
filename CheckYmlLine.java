public class CheckYmlLine {
  private final String line;
  private boolean done;
  private boolean keyFound;
  private String key;
  private String val;

  public CheckYmlLine(String line) {
    this.line = line;
  }

  public boolean keyFound() throws Exception {
    find();
    return keyFound;
  }

  public String key() throws Exception {
    find();

    if (!keyFound) {
      throw new Exception("YML line \"" + line + "\" does not have any key.");
    }

    return key;
  }

  public String value() throws Exception {
    find();

    if (!keyFound) {
      throw new Exception("YML line \"" + line + "\" does not have any key.");
    }

    return val;
  }

  private void find() throws Exception {
    if (done) {
      return;
    }

    done = true;

    String trimmed = line.trim();

    if (trimmed.startsWith("# ") || trimmed.isEmpty()) {
      return;
    }

    String splitter = ": ";
    int ix = trimmed.indexOf(splitter);

    if (ix < 0) {
      throw new Exception("YML line \"" + line + "\" does not have any key.");
    }

    if (ix == 0) {
      throw new Exception("Wrong (empty) YML key in line: \"" + line + "\".");
    }

    keyFound = true;
    key = trimmed.substring(0, ix);
    val = trimmed.substring(ix + splitter.length());
  }
}