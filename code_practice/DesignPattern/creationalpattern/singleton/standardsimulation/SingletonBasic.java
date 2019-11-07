package standardsimulation;

/**
 * SingletonBasic
 */
// 基本款，線程不安全
public class SingletonBasic {

  private static SingletonBasic singletonBasic;

  private SingletonBasic() {}

  public static SingletonBasic getInstance() {
    if(singletonBasic == null) {
      System.out.println("ask for first time");
      singletonBasic = new SingletonBasic();
    }
    return singletonBasic;
  }

  public static void main(String[] args) {
    for(int i=0 ; i<10 ; i++) {
      System.out.println(i + ": "+ SingletonBasic.getInstance());
    }
  }
}