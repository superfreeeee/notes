/**
 * Room
 */
public class Room implements MapSite {

  private int rommNO;
  private MapSite[] sides = new MapSite[4];

  public Room(int rommNO) {
    System.out.println("new Room(" + rommNO + ")");
    this.rommNO = rommNO;
  }

  public void setSide(Direction direction, MapSite side) {
    int index = -1;
    switch(direction) {
      case EAST: index = 0; break;
      case WEST: index = 1; break;
      case SOUTH: index = 2; break;
      case NORTH: index = 3; break;
    }
    if(index != -1)
      sides[index] = side;
    System.out.println(this + ": set (" + direction + ", " + side + ")");
  }

  @Override
  public void enter() {
    System.out.println("enter " + this);
  }

  /**
   * @return the rommNO
   */
  public int getRommNO() {
    return rommNO;
  }

  @Override
  public String toString() {
    return "room " + rommNO;
  }

  public void printSides() {
    System.out.println("East: " + sides[0]);
    System.out.println("West: " + sides[1]);
    System.out.println("South: " + sides[2]);
    System.out.println("North: " + sides[3]);
  }

  public static void main(String[] args) {
    Room room = new Room(0);
    room.setSide(Direction.EAST, new Room(10));
    System.out.println(room);
    room.printSides();
  }
}