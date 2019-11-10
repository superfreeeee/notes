/**
 * AdapterObject
 */
public class AdapterObject implements Target {

  private Adaptee adaptee;

  public AdapterObject(Adaptee adaptee) {
    this.adaptee = adaptee;
  }

  @Override
  public void request() {
    System.out.println("trigger target.request()");
    System.out.println("give controll authority to Adaptee");
    adaptee.specificRequest();
  }

  public static void main(String[] args) {
    Target target = new AdapterObject(new Adaptee());
    target.request();
  }

}