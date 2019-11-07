/**
 * MySQLUser
 */
public class OracleUser implements IUser {

  @Override
  public void insert(User user) {
    System.out.println("Insert into User in Oracle");
  }

  @Override
  public User getUserById(int id) {
    System.out.println("Get User from User in Oracle");
    return null;
  }
}