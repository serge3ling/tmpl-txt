public class App {
  public static void main(String[] args) throws Exception {
    String appName = "Template Text App";
    System.out.println(appName + " started.");

    OverallWriter writer =
        (args.length > 0) ?
        new OverallWriter(args[0]) :
        new OverallWriter(new Cnf().trgDir());
    
    writer.write();

    System.out.println(appName + " done.");
  }
}