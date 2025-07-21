public class Template {
  private final String src;
  private boolean done = false;
  private String filename = "stub-name.xml";
  private boolean writeTail = false;

  public Template(String key, String val) {
    this.filename = key;
    this.src = val;
  }

  public String filename() {
    read();
    return filename;
  }

  public boolean toWriteTail() {
    read();
    return writeTail;
  }

  private void read() {
    if (done) {
      return;
    }

    done = true;

    String[] split = src.split(",");

    for (String string : split) {
      fetch(string);
    }
  }

  private void fetch(String string) {
    String[] split = string.split("=");
    
    if (split.length != 2) {
      return;
    }

    String split0 = split[0].trim().toLowerCase();
    String split1 = split[1].trim();

    if ((split0.equals("name") || split0.equals("filename")) && !split1.isEmpty()) {
      this.filename = split1;
    } else if (split0.equals("write") && split1.equals("tail")) {
      writeTail = true;
    }
  }
}