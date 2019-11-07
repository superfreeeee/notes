/**
 * Wall
 */
public class Wall implements MapSite {

  private static int count;
  private int id;

  public Wall() {
    System.out.println("new Wall()");
    this.id = count++;
  }

  @Override
  public void enter() {
    System.out.println("encounter a wall");
  }

  @Override
  public String toString() {
    return "Wall " + id;
  }
}