import java.util.ArrayList;
import java.util.List;

interface LinesRead {
  List<String> read() throws Exception;

  public class Stub implements LinesRead {
    private final List<String> lines;

    public Stub(List<String> lines) {
      this.lines = lines;
    }

    @Override
    public List<String> read() throws Exception {
      return lines;
    }
  }
}