import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MyLinkedList
 */
public class MyLinkedList<T> implements Iterable<T>{

  public MyLinkedList() {
    doClear();
  }

  public void clear() {
    doClear();
  }

  private void doClear() {
    head = new Node<T>(null, null, null);
    tail = new Node<T>(null, head, null);
    head.next = tail;
    theSize = 0;
    modCount++;
  }

  public int size() {
    return theSize;
  }

  public boolean isEmpty() {
    return theSize == 0;
  }

  public boolean add(T item) {
    add(theSize, item);
    return true;
  }

  public void add(int index, T item) {
    addBefore(getNode(index, 0, theSize), item);
  }

  public T get(int index) {
    return getNode(index).data;
  }

  public T set(int index, T item) {
    Node<T> p = getNode(index);
    T old = p.data;
    p.data = item;
    return old;
  }

  public T remove(int index) {
    return remove(getNode(index));
  }

  public Iterator<T> iterator() {
    return new LinkedListIterator();
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    StringBuilder result1 = new StringBuilder();
    for(Node<T> p=head.next ; p!=tail ; p = p.next) {
      result1.append(p.data);
      result1.append(" -> ");
    }
    StringBuilder result2 = new StringBuilder();
    for(Node<T> p=tail.prev ; p!=head ; p = p.prev) {
      result2.append(p.data);
      result2.append(" -< ");
    }
    return "from head: {" + result1.toString() + "}\nfrom tail: {" + result2.reverse().toString() + "}";
  }

  private class LinkedListIterator implements Iterator<T> {
    public boolean hasNext() {
      return current.next != null;
    }

    public T next() {
      if(!hasNext())
        throw new NoSuchElementException();
      return (current = current.next).data;
    }

    public void remove() {
      current.prev.next = current.next;
      if(current.next != null)
        current.next.prev = current.prev;
    }

    private Node<T> current = head;
  }

  private void addBefore(Node<T> p, T item) {
    Node<T> newNode = new Node<>(item, p.prev, p);
    newNode.prev.next = newNode;
    p.prev = newNode;
    theSize++;
    modCount++;
  }

  private T remove(Node<T> p) {
    p.next.prev = p.prev;
    p.prev.next = p.next;
    theSize--;
    modCount++;

    return p.data;
  }

  private Node<T> getNode(int index) {
    return getNode(index, 0, size()-1);
  }

  private Node<T> getNode(int index, int lower, int upper) {
    Node<T> p;
    if(index < lower || index > upper)
      throw new IndexOutOfBoundsException();
    if(index < size() / 2) {
      p = head.next;
      for(int i=0 ; i<index ; i++)
        p = p.next;
    } else {
      p = tail;
      for(int i=size() ; i>index ; i--)
        p = p.prev;
    }

    return p;
  }

  private static class Node<T> {
    public Node(T data, Node<T> prev, Node<T> next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }

    public T data;

    public Node<T> prev;

    public Node<T> next;
  }

  private int theSize;

  private int modCount = 0;

  private Node<T> head;

  private Node<T> tail;

  public static void main(String[] args) {
    MyLinkedList<String> list = new MyLinkedList<>();
    for(int i=0 ; i<10 ; i++) {
      list.add(String.valueOf(i));
      System.out.println(list);
      System.out.println(list.size());
    }
    System.out.println(list.get(3));
    System.out.println(list.get(8));
    System.out.println(list.size());
    list.remove(7);
    System.out.println(list);
    list.remove(4);
    System.out.println(list.size());
    System.out.println(list.isEmpty());
    System.out.println(list);
    list.remove(6);
    System.out.println(list);
    list.clear();
    System.out.println(list);
    System.out.println(list.size());
    System.out.println(list.isEmpty());
  }
}