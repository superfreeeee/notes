/**
 * Client
 */
public class Client {

  public static void main(String[] args) {
    User user = new User();
    Type type = new Type();

    IFactory iFactory = new MySQLFactory();
    IUser iu = iFactory.createIUser();
    iu.insert(user);
    iu.getUserById(1);

    IType it = iFactory.createIType();
    it.insert(type);
    it.getTypeById(1);


    iFactory = new OracleFactory();
    iu = iFactory.createIUser();
    iu.insert(user);
    iu.getUserById(1);

    it = iFactory.createIType();
    it.insert(type);
    it.getTypeById(1);
  }
}