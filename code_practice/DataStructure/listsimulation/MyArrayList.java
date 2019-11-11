import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MyArrayList
 */
public class MyArrayList<T> implements Iterable<T> {

  public MyArrayList() {
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

  public void trimToSize() {
    ensureCapacity(size());
  }

  public T get(int index) {
    if(index < 0 || index >= size())
      throw new ArrayIndexOutOfBoundsException();
    return theItems[index];
  }

  public T set(int index, T newVal) {
    if(index < 0 || index >= size())
      throw new ArrayIndexOutOfBoundsException();
    T oldVal = theItems[index];
    theItems[index] = newVal;
    return oldVal;
  }

  public void ensureCapacity(int newCapacity) {
    if(newCapacity < theSize)
      return;

    T[] oldVals = theItems;
    theItems = (T[]) new Object[newCapacity];
    for(int i=0 ; i<size() ; i++)
      theItems[i] = oldVals[i];
  }

  public boolean add(T item) {
    add(size(), item);
    return true;
  }

  public void add(int index, T item) {
    if(theItems.length == size())
      ensureCapacity(size() * 2 + 1);
    for(int i=theSize ; i>index ; i--)
      theItems[i] = theItems[i-1];
    theItems[index] = item;
    
    theSize++;
  }

  public T remove(int index) {
    T removeItem = theItems[index];
    for(int i=index ; i < size()-1 ; i++)
      theItems[i] = theItems[i+1];
    
    theSize--;
    return removeItem;
  }

  public Iterator<T> iterator() {
    return new ArrayListIterator();
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    StringBuilder result = new StringBuilder();
    result.append("{");
    for(int i=0 ; i<size() ; i++) {
      result.append(theItems[i]);
      result.append(", ");
    }
    result.append("}");
    return result.toString();
  }

  private class ArrayListIterator implements Iterator<T> {

    public boolean hasNext() {
      return current < size();
    }

    public T next() {
      if(!hasNext())
        throw new NoSuchElementException();
      return theItems[current++];
    }

    public void remove() {
      MyArrayList.this.remove(--current);
    }

    private int current = 0;
  }

  private static final int DEFAULT_CAPACITY = 10;

  private int theSize;
  private T[] theItems;

  private void doClear() {
    theSize = 0;
    ensureCapacity(DEFAULT_CAPACITY);
  }

  public static void main(String[] args) {
    MyArrayList<Integer> list = new MyArrayList<>();
    for(int i=1 ; i<=1024 ; i*=2) {
      list.add(i);
      System.out.println(list);
    }
    Iterator<Integer> iter = list.iterator();
    int i=0;
    while(iter.hasNext()) {
      System.out.println(iter.next());
      if(i++ == 4)
        iter.remove();
    }
    System.out.println(list);
    list.remove(3);
    System.out.println(list);
    System.out.println(list.size());
    System.out.println(list.isEmpty());
    list.clear();
    System.out.println(list);
    System.out.println(list.size());
    System.out.println(list.isEmpty());

  }
}