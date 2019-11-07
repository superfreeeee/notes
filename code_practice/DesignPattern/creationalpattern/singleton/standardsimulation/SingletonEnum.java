package standardsimulation;

public enum SingletonEnum {
  INSTANCE;
  public void get() {
    System.out.println("get instance " + this);
  }

  public static void main(String[] args) {
    SingletonEnum singletonEnum = SingletonEnum.INSTANCE;
    singletonEnum.get();
    singletonEnum = SingletonEnum.INSTANCE;
    singletonEnum.get();
    singletonEnum = SingletonEnum.INSTANCE;
    singletonEnum.get();
    singletonEnum = SingletonEnum.INSTANCE;
    singletonEnum.get();
  }
}