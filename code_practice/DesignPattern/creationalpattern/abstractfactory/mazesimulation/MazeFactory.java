/**
 * MazeFactory
 */
public class MazeFactory {

  public Maze makeMaze() {
    return new Maze();
  }  

  public Wall makeWall() {
    return new Wall();
  }

  public Room makeRoom(int roomNO) {
    return new Room(roomNO);
  }

  public Door makeDoor(Room r1, Room r2) {
    return new Door(r1, r2);
  }
}