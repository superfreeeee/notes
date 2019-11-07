/**
 * MazeGameWithFactoryMethod
 */
public class MazeGameWithFactoryMethod extends MazeGame {

  @Override
  public Maze createMaze() {
    Maze maze = makeMaze();
    Room r1 = makeRoom(1);
    Room r2 = makeRoom(2);
    Door d = makeDoor(r1, r2);

    maze.addRoom(r1);
    maze.addRoom(r2);

    r1.setSide(Direction.EAST, d);
    r1.setSide(Direction.WEST, makeWall());
    r1.setSide(Direction.SOUTH, makeWall());
    r1.setSide(Direction.NORTH, makeWall());

    r2.setSide(Direction.EAST, makeWall());
    r2.setSide(Direction.WEST, d);
    r2.setSide(Direction.SOUTH, makeWall());
    r2.setSide(Direction.NORTH, makeWall());

    return maze;
  }

  public Maze makeMaze() {
    return new Maze();
  }

  public Room makeRoom(int roomNO) {
    return new Room(roomNO);
  }

  public Wall makeWall() {
    return new Wall();
  }

  public Door makeDoor(Room r1, Room r2) {
    return new Door(r1, r2);
  }

  public static void main(String[] args) {
    MazeGameWithFactoryMethod mazeGameWithFactoryMethod = new MazeGameWithFactoryMethod();
    Maze maze = mazeGameWithFactoryMethod.createMaze();
  }
}