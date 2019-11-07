/**
 * StandardMazeBuilder
 */
public class StandardMazeBuilder implements MazeBuilder {

  private Maze template;

  public void buildMaze() {
    template = new Maze();
  }

  public void buildRoom(int roomNO) {
    if(template != null && template.getRoomByRoomNO(roomNO) == null) {
      Room room = new Room(roomNO);
      template.addRoom(room);

      room.setSide(Direction.EAST, new Wall());
      room.setSide(Direction.WEST, new Wall());
      room.setSide(Direction.SOUTH, new Wall());
      room.setSide(Direction.NORTH, new Wall());
    }
  }

  public void buildDoor(int roomNO1, int roomNO2) {
    Room r1 = template.getRoomByRoomNO(roomNO1);
    Room r2 = template.getRoomByRoomNO(roomNO2);
    Door d = new Door(r1, r2);

    r1.setSide(determineDirection(r1, r2), d);
    r2.setSide(determineDirection(r2, r1), d);
  }

  private Direction determineDirection(Room r1, Room r2) {
    int roomNO1 = r1.getRommNO();
    int roomNO2 = r2.getRommNO();

    if(roomNO2 - roomNO1 > 0)
      return Direction.EAST;
    else if(roomNO2 - roomNO1 < 0)
      return Direction.WEST;
    else
      return null;
  }

  public Maze build() {
    return template;
  }
}