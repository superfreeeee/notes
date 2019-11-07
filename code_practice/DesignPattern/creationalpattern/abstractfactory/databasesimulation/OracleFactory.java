/**
 * MySQLFactory
 */
public class OracleFactory implements IFactory {

  @Override
  public IUser createIUser() {
    return new OracleUser();
  }
  
  @Override
  public IType createIType() {
    return new OracleType();
  }
}