import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class LinesFile {
  private final String filename;
  private final File file;

  public LinesFile(String filename) {
    this.filename = filename;
    this.file = new File(filename);
  }

  public void overwrite(List<String> lines) throws Exception {
    PrintWriter writer = new PrintWriter(
      new BufferedWriter(
        new FileWriter(file)
      )
    );
    write(lines, writer);
    writer.close();
  }

  public void writeTail(List<String> lines) throws Exception {
    PrintWriter writer = new PrintWriter(
      new BufferedWriter(
        new FileWriter(file, true)
      )
    );
    write(lines, writer);
    writer.close();
  }

  private void write(List<String> lines, PrintWriter writer) {
    for (String line : lines) {
      writer.println(line);
    }
  }
}