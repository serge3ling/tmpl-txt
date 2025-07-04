import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class IdGetFile implements IdGet {
  private final String filename = "id.txt";
  private final File file = new File(filename);
  private final IdInc idInc = new IdInc(4);

  public String next() throws Exception {
    String id = readId();
    String nextId = idInc.next(id);
    saveId(nextId);
    return nextId;
  }

  private String readId() throws Exception {
    if (!file.exists()) {
      throw new Exception("File \"" + filename + "\" is not there.");
    }

    String id = "stub_id__do_not_believe_it";

    // Note: A try-with-resources statement can have catch and finally blocks just like an ordinary try statement. In a try-with-resources statement, any catch or finally block is run after the resources declared have been closed.
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line = reader.readLine();
      if (line == null) {
        throw new Exception("Could not read line from file \"" + filename + "\"");
      } else {
        id = line.trim();
      }
    } catch (Exception ex) {
      throw new Exception("Could not read line from file \"" + filename + "\"", ex);
    }

    return id;
  }

  private void saveId(String id) throws Exception {
    try (PrintWriter writer = new PrintWriter(file)) {
      writer.write(id);
    } catch (Exception ex) {
      throw new Exception("Could not write to file \"" + filename + "\"", ex);
    }
  }
}