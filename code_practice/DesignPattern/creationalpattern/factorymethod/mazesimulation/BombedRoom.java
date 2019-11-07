/**
 * BombedRoom
 */
public class BombedRoom extends Room {

  private boolean possibleExplored = true;
  
  public BombedRoom(int roomNO) {
    super(roomNO);
    System.out.println("new BombedRoom(" + roomNO + ")");
  }

  @Override
  public void enter() {
    System.out.println("enter a room possible explore");
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "BombedRoom {" + super.toString() + ", possibleExplored: " + possibleExplored + "}";
  }
}