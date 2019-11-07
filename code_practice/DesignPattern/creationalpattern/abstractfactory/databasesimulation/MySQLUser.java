/**
 * MySQLUser
 */
public class MySQLUser implements IUser {

  @Override
  public void insert(User user) {
    System.out.println("Insert into User in MySQL");
  }

  @Override
  public User getUserById(int id) {
    System.out.println("Get User from User in MySQL");
    return null;
  }
}