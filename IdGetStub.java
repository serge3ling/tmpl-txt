public class IdGetStub implements IdGet {
  public static final String startId = "zelene1zhyto2za3selom4z0000";
  private int i = 0;
  private String id = startId;
  private final IdInc idInc = new IdInc(4);

  public String next() throws Exception {
    id = idInc.next(id);
    return id;
  }
}