import java.util.ArrayList;

/**
 * Maze
 */
public class Maze {

  private ArrayList<Room> rooms = new ArrayList<>(); 

  public Maze() {
    System.out.println("new Maze()");
  }

  public void addRoom(Room room) {
    System.out.println("add " + room);
    rooms.add(room);
  }

  public Room getRoomByRoomNO(int roomNO) {
    for(Room room : rooms)
      if(room.getRommNO() == roomNO)
        return room;
    return null;
  }
}