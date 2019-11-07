/**
 * Test
 */
public class Test {

  public static void main(String[] args) {
    Manager manager = new Manager();
    UnderlinePen underlinePen = new UnderlinePen('~');
    MessageBox messageBox1 = new MessageBox('*');
    MessageBox messageBox2 = new MessageBox('/');
    manager.register("strong name", underlinePen);
    manager.register("warning box", messageBox1);
    manager.register("slash box", messageBox2);

    Product p1 = manager.create("strong name");
    p1.use("Hello World!");
    System.out.println();
    Product p2 = manager.create("warning box");
    p2.use("Hello World!");
    System.out.println();
    Product p3 = manager.create("slash box");
    p3.use("Hello World!");
    System.out.println();
  }
}