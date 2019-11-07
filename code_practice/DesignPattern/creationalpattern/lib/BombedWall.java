/**
 * BombedWall
 */
public class BombedWall extends Wall {

  public BombedWall() {
    super();
    System.out.println("new BombedWall()");
  }

  @Override
  public void enter() {
    System.out.println("encounter a wall with bomb");
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "Bombed Wall {" + super.toString() + "}";
  }
}