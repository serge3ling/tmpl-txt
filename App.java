import java.util.ArrayList;
import java.util.List;

public class App {
  public static void main(String[] args) throws Exception {
    String appName = "Template Text App";
    System.out.println(appName + " started.");

    List<IdGet> idGets = new ArrayList<>();
    idGets.add(new IdGetFile());
    idGets.add(new IdGetFile("id2.txt", new IdIncDigit(5)));
    idGets.add(new IdGetFile("id3.txt", new IdIncDigit(5)));

    OverallWriter writer =
        (args.length > 0) ?
        new OverallWriter(args[0]) :
        new OverallWriter(new Cnf().trgDir(), idGets);
    
    writer.write();

    System.out.println(appName + " done.");
  }
}