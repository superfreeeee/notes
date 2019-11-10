/**
 * Adapter
 */
public class AdapterInClass extends TypeCImp1 implements MicroUSB {

  @Override
  public void isMicroUSB() {
    isTypeC();
  }

  public static void main(String[] args) {
    MicroUSB microUSB = new AdapterInClass();
    microUSB.isMicroUSB();
  }
}