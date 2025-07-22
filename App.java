public class App {
  public static void main(String[] args) throws Exception {
    String appName = "Template Text App";
    System.out.println(appName + " started.");

/* // This is how many id getters can be used.
    List<IdGet> idGets = new ArrayList<>();
    idGets.add(new IdGetFile());
    idGets.add(new IdGetFile("id2.txt", new IdInc(4)));
    idGets.add(new IdGetFile("id3.txt", new IdInc(4)));
*/
    OverallWriter writer =
        (args.length > 0) ?
        new OverallWriter(args[0]) :
        new OverallWriter(new Cnf().trgDir()/*, idGets*/);
    
    writer.write();

    System.out.println(appName + " done.");
  }
}