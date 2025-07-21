import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileLines implements LinesRead {
  private final String filename;
  private final File file;

  public FileLines(String filename) {
    this.filename = filename;
    this.file = new File(filename);
  }

  @Override
  public List<String> read() throws Exception {
    if (!file.exists()) {
      throw new Exception("File \"" + filename + "\" is not there.");
    }

    List<String> lines = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line = reader.readLine();

      if (line == null) {
        reader.close();
        return lines;
      }

      while (line != null) {
        lines.add(line);
        line = reader.readLine();
      }
    }

    return lines;
  }
}