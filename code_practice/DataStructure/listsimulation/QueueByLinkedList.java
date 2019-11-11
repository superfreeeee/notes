/**
 * QueueByLinkedList
 */
public class QueueByLinkedList<T> extends MyLinkedList<T> {

  public boolean enqueue(T item) {
    add(item);
    return true;
  }

  public T dequeue() {
    return remove(0);
  }

  public static void main(String[] args) {
    QueueByLinkedList<String> queue = new QueueByLinkedList<>();
    for(int i=0 ; i<10 ; i++) {
      System.out.println("enqueue: " + i);
      queue.enqueue(String.valueOf(i));
      System.out.println(queue);
    }
    for(int i=0 ; i<10 ; i++) {
      System.out.println("dequeue: " + queue.dequeue());
      System.out.println(queue);
    }
  }
}