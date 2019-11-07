/**
 * MySQLUser
 */
public class OracleType implements IType {

  @Override
  public void insert(Type type) {
    System.out.println("Insert into Type in Oracle");
  }

  @Override
  public Type getTypeById(int id) {
    System.out.println("Get Type from User in Oracle");
    return null;
  }
}