/**
 * UnderlinePen
 */
public class UnderlinePen implements Product {

  private char ulchar;

  public UnderlinePen(char ulchar) {
    this.ulchar = ulchar;
  }

  @Override
  public void use(String s) {
    // TODO Auto-generated method stub
    int lenght = s.getBytes().length;
    System.out.println("\""+s+"\"");
    System.out.print(" ");
    for(int i=0;i<lenght;i++){
      System.out.print(ulchar);
    }
    System.out.println();
  }

  @Override
  public Product createClone() {
    // TODO Auto-generated method stub
    Product p = null;
    try {
      p = (Product)clone();
    } catch (CloneNotSupportedException e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return p;
  }

  public static void main(String[] args) {
    UnderlinePen underlinePen = new UnderlinePen('^');
    underlinePen.use("123456");
  }
}