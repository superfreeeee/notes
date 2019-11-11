/**
 * StackByLinkedList
 */
public class StackByLinkedList<T> extends MyLinkedList<T> {

  public boolean push(T item) {
    add(0, item);
    return true;
  }

  public T pop() {
    return remove(0);
  }

  public static void main(String[] args) {
    StackByLinkedList<String> stack = new StackByLinkedList<>();
    for(int i=0 ; i<64 ; i+=2) {
      stack.push(String.valueOf(i));
      System.out.println(stack);
      if(i % 8 == 0) {
        String temp = stack.pop();
        System.out.println("pop " + temp);
        System.out.println(stack);
      }
      System.out.println(stack.size());
    }
    System.out.println(stack);
    System.out.println(stack.size());
  }
}