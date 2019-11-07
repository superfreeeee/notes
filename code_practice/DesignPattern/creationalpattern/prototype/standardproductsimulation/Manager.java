import java.util.HashMap;

/**
 * Manager
 */
public class Manager {

  private HashMap<String, Product> showcase = new HashMap<>();

  public void register(String name, Product p) {
    showcase.put(name, p);
  }

  public Product create(String name) {
    Product product = (Product)showcase.get(name);
    return product.createClone();
  }
}