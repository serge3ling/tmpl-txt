public class Replace implements ReplaceCommon {
  public String gL() {
    return "${";
  }

  public String r() {
    return "}";
  }

  public String id() {
    return "id_autoinc_";
  }

  public String replace(String s, String name, String val) {
    return s.replace(gL() + name + r(), val);
  }
}