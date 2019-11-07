package standardsimulation;

/**
 * SingletonSync
 */
// 添加線程安全
public class SingletonSync {

  private static SingletonSync singletonSync;

  private SingletonSync() {}

  public static synchronized SingletonSync getInstance() {
    if(singletonSync == null) {
      System.out.println("ask for first time");
      singletonSync = new SingletonSync();
    }
    return singletonSync;
  }

  public static void main(String[] args) {
    for(int i=0 ; i<10 ; i++) {
      System.out.println(i + ": "+ SingletonSync.getInstance());
    }
  }
}