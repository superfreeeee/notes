/**
 * MazeGameWithBuilder
 */
public class MazeGameWithBuilder {

  public Maze createMaze(MazeBuilder mazeBuilder) {
    mazeBuilder.buildMaze();
    mazeBuilder.buildRoom(1);
    mazeBuilder.buildRoom(2);
    mazeBuilder.buildDoor(1, 2);
    return mazeBuilder.build();
  }

  public Maze createComplexMaze(MazeBuilder mazeBuilder) {
    mazeBuilder.buildMaze();
    for(int i=0 ; i<100 ; i++)
      mazeBuilder.buildRoom(i);
    return mazeBuilder.build();
  }

  public static void main(String[] args) {
    MazeBuilder mazeBuilder = new StandardMazeBuilder();
    MazeGameWithBuilder mazeGameWithBuilder = new MazeGameWithBuilder();
    Maze maze = mazeGameWithBuilder.createMaze(mazeBuilder);
    // Maze complexMaze = mazeGameWithBuilder.createComplexMaze(mazeBuilder);
    System.out.println();
    mazeBuilder = new CountingMazeBuilder();
    maze = mazeGameWithBuilder.createMaze(mazeBuilder);
  }
}