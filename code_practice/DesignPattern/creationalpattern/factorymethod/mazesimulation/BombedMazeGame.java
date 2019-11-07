/**
 * BombedMazeGame
 */
public class BombedMazeGame extends MazeGameWithFactoryMethod {

  @Override
  public Wall makeWall() {
    return new BombedWall();
  }

  @Override
  public Room makeRoom(int roomNO) {
    return new BombedRoom(roomNO);
  }

  
}