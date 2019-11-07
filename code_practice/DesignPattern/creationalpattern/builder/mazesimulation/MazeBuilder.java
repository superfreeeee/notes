/**
 * MazeBuilder
 */
public interface MazeBuilder {

 void buildMaze();

 void buildRoom(int roomNO);

 void buildDoor(int r1, int r2);

 Maze build();
}