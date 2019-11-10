/**
 * AdapterInObject
 */
public class AdapterInObject implements MicroUSB {

  TypeC typeC;

  public AdapterInObject(TypeC typeC) {
    this.typeC = typeC;
  }

  @Override
  public void isMicroUSB() {
    typeC.isTypeC();
  }
  
  public static void main(String[] args) {
    MicroUSB microUSB = new AdapterInObject(new TypeCImp1());  
    microUSB.isMicroUSB();
  }

}