/**
 * StackByArray
 */
public class StackByArray<T> {

  public StackByArray() {
    doClear();
  }

  public void clear() {
    doClear();
  }

  private void doClear() {
    top = -1;
    ensureCapacity(DEFAULT_CAPACITY);
  }

  public int size() {
    return top + 1;
  }

  public boolean isEmpty() {
    return top == -1;
  }

  public boolean push(T item) {
    if(top == theStack.length-1)
      ensureCapacity(theStack.length * 2);
    theStack[++top] = item;
    return true;
  }

  public T pop() {
    if(isEmpty())
      return null;
    return theStack[top--];
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    StringBuilder result = new StringBuilder();
    for(int i=0 ; i<= top ; i++) {
      result.append("--");
    }
    result.append("-\n|");
    for(int i=top ; i>=0 ; i--) {
      result.append(" ");
      result.append(theStack[i]);
    }
    result.append("\n-");
    for(int i=0 ; i<= top ; i++) {
      result.append("--");
    }
    return result.toString() + "\n";
  }

  private void ensureCapacity(int size) {
    T[] old = theStack;
    theStack = (T[]) new Object[size];
    for(int i=0 ; i<=top ; i++) {
      theStack[i] = old[i];
    }
  }

  private static final int DEFAULT_CAPACITY = 10;

  private int top = -1;

  private T[] theStack;

  public static void main(String[] args) {
    StackByArray<String> stack = new StackByArray<>();
    for(int i=0 ; i<10 ; i++) {
      System.out.println("push: " + i);
      stack.push(String.valueOf(i));
      System.out.println(stack);
      if(i % 3 == 0) {
        System.out.println("pop: " + stack.pop());
        System.out.println(stack);
      }
    }
    
  }
}