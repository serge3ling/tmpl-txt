public class App {
  public static void main(String[] args) throws Exception {
    System.out.println("make-covs App started.");

    OverallWriter writer =
        (args.length > 0) ?
        new OverallWriter(args[0]) :
        new OverallWriter("C:/files/docs/tasks/2025/595994-product-model/self-made");
    
    writer.write();

    System.out.println("make-covs App done.");
  }
}