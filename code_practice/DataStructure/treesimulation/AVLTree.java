/**
 * AVLTree
 */
public class AVLTree<T extends Comparable<? super T>> {

  public AVLTree() {
    root = null;
  }

  public void insert(T item) {
    root = insert(item, root);
  }

  private AVLNode<T> insert(T item, AVLNode<T> t) {
    if(t == null)
      return new AVLNode<>(item, null, null);
    int compareResult = item.compareTo(t.element);
    if(compareResult < 0) {
      t.left = insert(item, t.left);
    } else if(compareResult > 0) {
      t.right = insert(item, t.right);
    } else {

    }
    return balance(t);
  }

  private AVLNode<T> balance(AVLNode<T> t) {
    if(t == null)
      return t;
    if(height(t.left) - height(t.right) > ALLOWEB_IMBALANCE) {
      if(height(t.left.left) >= height(t.left.right)) {
        t = rotateLL(t);
      } else {
        t = rotateLR(t);
      }
    } else if(height(t.right) - height(t.left) > ALLOWEB_IMBALANCE) {
      if(height(t.right.left) >= height(t.right.right)) {
        t = rotateRL(t);
      } else {
        t = rotateRR(t);
      }
    }
    t.height = Math.max(height(t.left), height(t.right)) + 1;
    return t;
  }

  private AVLNode<T> rotateLL(AVLNode<T> k2) {
    AVLNode<T> k1 = k2.left;
    k2.left = k1.right;
    k1.right = k2;
    k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
    k1.height = Math.max(height(k1.left), k2.height) + 1;
    return k1;
  }

  private AVLNode<T> rotateLR(AVLNode<T> k3) {
    k3.left = rotateRR(k3.left);
    return rotateLL(k3);
  }

  private AVLNode<T> rotateRL(AVLNode<T> k3) {
    k3.right = rotateLL(k3.right);
    return rotateRR(k3);
  }

  private AVLNode<T> rotateRR(AVLNode<T> k2) {
    AVLNode<T> k1 = k2.right;
    k2.right = k1.left;
    k1.left = k2;
    k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
    k1.height = Math.max(height(k1.left), k2.height) + 1;
    return k1;
  }

  public void printTree() {
    StringBuilder result = new StringBuilder();
    printTree(root, result, 0);
    result.append("\n");
    System.out.println(result);
}

  private void printTree(AVLNode<T> t, StringBuilder result, int offset) {
    addOffset(result, offset);
    if(t != null) {
      result.append(t.element);
      if(t.left == null && t.right == null) {
        return;
      }
      result.append("\n");
      printTree(t.left, result, offset + 2);
      result.append("\n");
      printTree(t.right, result, offset + 2);
    } else {
      result.append("null");
    }
  }

  private void addOffset(StringBuilder result, int offset) {
    for(int i=0 ; i<offset ; i++)
      result.append(" ");
  }

  private static class AVLNode<T> {

    AVLNode(T element) {
      this(element, null, null);
    }

    AVLNode(T element, AVLNode<T> left, AVLNode<T> right) {
      this.element = element;
      this.left = left;
      this.right = right;
      this.height = 0;
    }

    T element;

    AVLNode<T> left;

    AVLNode<T> right;

    int height;
  }

  private int height(AVLNode<T> t) {
    return t == null ? -1 : t.height;
  }

  private static final int ALLOWEB_IMBALANCE = 1;

  private AVLNode<T> root;

  public static void main(String[] args) {
    AVLTree<Integer> tree = new AVLTree<>();
      int[] sequence = new int[]{1,3,5,7,9,6};
      for(int i : sequence)
        tree.insertAndPrint(i);

      // sequence = new int[]{1,2,10,15,12,14,3,13,11,9,8};
      // for(int i : sequence)
      //   tree.removeAndPrint(i);
  }

  public void insertAndPrint(T item) {
    insert(item);
    System.out.println("insert: " + item);
    printTree();
  }
  
}