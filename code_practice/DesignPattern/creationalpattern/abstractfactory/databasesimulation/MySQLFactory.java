/**
 * MySQLFactory
 */
public class MySQLFactory implements IFactory {

  @Override
  public IUser createIUser() {
    return new MySQLUser();
  }
  
  @Override
  public IType createIType() {
    return new MySQLType();
  }
}