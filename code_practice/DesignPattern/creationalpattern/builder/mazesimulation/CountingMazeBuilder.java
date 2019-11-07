/**
 * CountingMazeBuilder
 */
public class CountingMazeBuilder extends StandardMazeBuilder {

  private int rooms;
  private int doors;
  
  @Override
  public Maze build() {
    printCounting();
    return super.build();
  }

  @Override
  public void buildRoom(int roomNO) {
    super.buildRoom(roomNO);
    rooms++;
  }

  @Override
  public void buildDoor(int roomNO1, int roomNO2) {
    super.buildDoor(roomNO1, roomNO2);
    doors++;
  }

  private void printCounting() {
    System.out.println("there are " + rooms + " rooms");
    System.out.println("there are " + doors + " doors");
  }
  
}