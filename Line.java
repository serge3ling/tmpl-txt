import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Line {
  private final List<IdGet> idGets;
  private final ReplaceCommon replace;
  private final Map<String, String> tagMap;
  private final Map<String, String> idMap = new HashMap<>();

  public Line(List<IdGet> idGets, ReplaceCommon replace, Map<String, String> tagMap) {
    this.idGets = idGets;
    this.replace = replace;
    this.tagMap = tagMap;
  }

  public void findIdsInOneLine(String line) throws Exception {
    String start = replace.gL() + replace.id();
    int ixStart = line.indexOf(start);

    if (ixStart < 0) {
      return;
    }

    String chopped = line.substring(ixStart + start.length());

    int ixEnd = chopped.indexOf(replace.r());

    if (ixEnd < 0) {
      return;
    }

    String idCut = chopped.substring(0, ixEnd);

    if (!idMap.containsKey(idCut)) {
      idMap.put(idCut, nextFor(idCut));
    }
  }

  private String nextFor(String idCut) throws Exception {
    int idGetNum = 0;
    String[] split = idCut.split("_");

    if (split.length >= 2) {
      try {
        idGetNum = Integer.parseInt(split[0]);
      } catch (Exception ex) {
        // skip
      }
    }

    return idGets.get(idGetNum).next();
  }

  public String replaceInOneLine(String line) {
    String rtn = line;

    if (!line.contains(replace.gL())) {
      return rtn;
    }

    // static tags like code, name
    for (String key : tagMap.keySet()) {
      rtn = replace.replace(rtn, key, tagMap.get(key));
    }

    // auto-inc ids
    for (String key : idMap.keySet()) {
      String wholeKey = replace.id() + key;
      rtn = replace.replace(rtn, wholeKey, idMap.get(key));
    }

    return rtn;
  }
}