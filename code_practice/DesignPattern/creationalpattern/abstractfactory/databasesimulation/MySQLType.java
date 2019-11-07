/**
 * MySQLUser
 */
public class MySQLType implements IType {

  @Override
  public void insert(Type type) {
    System.out.println("Insert into Type in MySQL");
  }

  @Override
  public Type getTypeById(int id) {
    System.out.println("Get Type from User in MySQL");
    return null;
  }
}