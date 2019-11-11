import java.util.Arrays;

/**
 * QueueByArray
 */
public class QueueByArray<T> {

  public QueueByArray() {
    doClear();    
  }

  public void clear() {
    doClear();
  }

  public int size() {
    return theSize;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public boolean enqueue(T item) {
    if(theSize >= theQueue.length)
      ensureCapacity(theQueue.length * 2);
    back = (back + 1) % theQueue.length;
    theQueue[back] = item;
    theSize++;
    return true;
  }

  public T dequeue() {
    if(theSize == 0)
      return null;
    T output = theQueue[front];
    front = (front + 1) % theQueue.length;
    theSize--;
    return output;
  }

  public void trimToSize() {
    ensureCapacity(theSize);
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    StringBuilder result = new StringBuilder();
    result.append("origin: ");
    result.append(Arrays.toString(theQueue));
    result.append("\nin sequence: [");
    for(int i=0, f=front ; i<theSize ; i++) {
      result.append(theQueue[f]);
      result.append(", ");
      f = (f + 1) % theQueue.length;
    }
    result.append("]\n");
    result.append("front: " + front + ", back: " + back + ", size: " + theQueue.length);
    return result.toString() + "\n";
  }

  private void doClear() {
    theSize = front = 0;
    back = -1;
    ensureCapacity(DEFAULT_CAPACITY);
  }

  private void ensureCapacity(int size) {
    T[] old = theQueue;
    theQueue = (T[]) new Object[size];
    for(int i=0 ; i<theSize ; i++) {
      theQueue[i] = old[front];
      front = (front + 1) % old.length;
    }
    front = 0;
    back = theSize-1;
  }

  private int front;

  private int back;

  private int theSize;

  private T[] theQueue;

  private static final int DEFAULT_CAPACITY = 10;

  public static void main(String[] args) {
    QueueByArray<String> queue = new QueueByArray<>();
    for(int i=0 ; i<100 ; i+=5) {
      System.out.println("enqueue: " + i);
      queue.enqueue(String.valueOf(i));
      System.out.println(queue);
    }
    for(int i=0 ; i<10 ; i++) {
      System.out.println("dequeue: " + queue.dequeue());
      System.out.println(queue);
      if(i >= 5) {
        System.out.println("enqueue: " + i*3);
        queue.enqueue(String.valueOf(i*3));
        System.out.println(queue);
      }
    }
    for(int i=0 ; i<10 ; i++) {
      System.out.println("enqueue: " + i);
      queue.enqueue(String.valueOf(i));
      System.out.println(queue);
    }
    queue.trimToSize();
    System.out.println(queue);
  }
}