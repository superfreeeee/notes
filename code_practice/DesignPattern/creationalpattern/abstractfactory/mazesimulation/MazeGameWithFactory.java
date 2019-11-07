/*
 * MazeGame
 */
public class MazeGameWithFactory extends MazeGame {

  public Maze createMaze(MazeFactory mazeFactory) {
    Maze maze = mazeFactory.makeMaze();
    Room r1 = mazeFactory.makeRoom(1);
    Room r2 = mazeFactory.makeRoom(2);
    Door d = mazeFactory.makeDoor(r1, r2);

    maze.addRoom(r1);
    maze.addRoom(r2);

    r1.setSide(Direction.EAST, d);
    r1.setSide(Direction.WEST, mazeFactory.makeWall());
    r1.setSide(Direction.SOUTH, mazeFactory.makeWall());
    r1.setSide(Direction.NORTH, mazeFactory.makeWall());

    r2.setSide(Direction.EAST, mazeFactory.makeWall());
    r2.setSide(Direction.WEST, d);
    r2.setSide(Direction.SOUTH, mazeFactory.makeWall());
    r2.setSide(Direction.NORTH, mazeFactory.makeWall());
    return maze;
  }

  public static void main(String[] args) {
    MazeGameWithFactory mazeGameWithFactory = new MazeGameWithFactory();
    MazeFactory mazeFactory = new MazeFactory();
    Maze maze = mazeGameWithFactory.createMaze(mazeFactory);
  }
}