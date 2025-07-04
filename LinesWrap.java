import java.util.ArrayList;
import java.util.List;

public class LinesWrap {
  private final Line lineWrap;
  private final List<String> lines;
  private final List<String> remade = new ArrayList<>();

  public LinesWrap(Line lineWrap, List<String> lines) {
    this.lineWrap = lineWrap;
    this.lines = lines;
  }

  public List<String> remake() throws Exception {
    findIdsInAllLines();
    replaceAll();
    return remade;
  }

  private void findIdsInAllLines() throws Exception {
    for (String line : lines) {
      lineWrap.findIdsInOneLine(line);
    }
  }

  private void replaceAll() {
    for (String line : lines) {
      remade.add(lineWrap.replaceInOneLine(line));
    }
  }
}