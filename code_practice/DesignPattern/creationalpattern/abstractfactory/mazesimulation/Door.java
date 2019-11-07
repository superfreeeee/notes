/**
 * Door
 */
public class Door implements MapSite {

  private Room room1;
  private Room room2;
  private boolean isOpened;

  public Door(Room room1, Room room2) {
    System.out.println("new Door(" + room1 + ", " + room2 + ")");
    this.room1 = room1;
    this.room2 = room2;
  }

  @Override
  public void enter() {
    System.out.println("enter room between " + room1 + " and " + room2);
  }

  @Override
  public String toString() {
    return "Door(" + room1 + ", " + room2 + ")";
  }

  public Room otherSideFrom(Room room) {
    if(room == room1)
      return room2;
    else if(room == room2)
      return room1;
    else
      return null;
  }
}