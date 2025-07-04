import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tags2Vals {
  private final String filename;
  private final File file;
  private final String splitter;

  public Tags2Vals() {
    this("tags2vals.txt", "\\|");
  }

  public Tags2Vals(String filename, String splitter) {
    this.filename = filename;
    this.file = new File(filename);
    this.splitter = splitter;
  }

  public List<Map<String, String>> maps() throws Exception {
    List<Map<String, String>> maps = new ArrayList<>();

    FileLines fileLines = new FileLines(filename);
    List<String> lines = fileLines.read();

    if (lines.size() < 2) {
      return maps;
    }

    String line0 = lines.get(0).trim();

    if (line0.isEmpty()) {
      return maps;
    }

    String[] keysAsIs = line0.split(splitter);
    String[] keys = new String[keysAsIs.length];

    for (int i = 0; i < keysAsIs.length; i++) {
      keys[i] = keysAsIs[i].trim();
      if (keys[i].isEmpty()) {
        throw new Exception("A key cannot be empty, line with keys: \"" + line0 + "\"");
      }
    }

    if (keys.length < 1) {
      return maps;
    }

    for (int i = 1; i < lines.size(); i++) {
      String trimmed = lines.get(i);

      if (trimmed.isEmpty()) {
        continue;
      }

      String[] valsAsIs = trimmed.split(splitter);
      String[] vals = new String[valsAsIs.length];

      for (int j = 0; j < valsAsIs.length; j++) {
        vals[j] = valsAsIs[j].trim();
      }

      if (vals.length != keys.length) {
        throw new Exception("Counts of keys (" + keys.length +
          ") and values (" + vals.length +
          ") are not the same. Keys: \"" +
          lines.get(0) + "\"; values: \"" + lines.get(i) + "\".");
      }

      Map<String, String> map = new HashMap<>();

      for (int j = 0; j < keys.length; j++) {
        map.put(keys[j], vals[j]);
      }

      maps.add(map);
    }

    return maps;
  }
}