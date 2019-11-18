/**
 * SplayTree
 */
public class SplayTree<T extends Comparable<? super T>> {

  public SplayTree() {
    root = null;
  }

  public void makeEmpty() {
    root = null;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public boolean contains(T item) {
    return contains(item, root);
  }

  private boolean contains(T item, SplayNode<T> node) {
    if(node == null)
      return false;
    int compareResult = item.compareTo(node.element);
    if(compareResult > 0) {
      return contains(item, node.right);
    } else if(compareResult < 0) {
      return contains(item, node.left);
    } else {
      return true;
    }
  }

  public void insert(T item) {
    root = insert(item, root);
  }

  private SplayNode<T> insert(T item, SplayNode<T> node) {
    if(node == null) {
      return new SplayNode<>(item, null, null);
    }
    int compareResult = item.compareTo(node.element);
    if(compareResult > 0) {
      node.right = insert(item, node.right);
    } else if(compareResult < 0) {
      node.left = insert(item, node.left);
    } else {

    }
    return node;
  }

  public void find(T item) {
    SplayNode<T> target = find(item, root);
    if(path == null) {
      System.out.println("not found");
    } else {
      System.out.println("found, do splay");
      root = splaying(find(item, root));
      System.out.println("after splay:");
      printTree();
    }
  }

  private SplayNode<T> find(T item, SplayNode<T> node) {
    if(node == null)
      return node;
    int compareResult = item.compareTo(node.element);
    if(compareResult > 0) {
      return find(item, node.right);
    } else if(compareResult < 0) {
      return find(item, node.left);
    } else {
      return node;
    }
  }

  private SplayNode<T> splaying(SplayNode<T> target) {
    SplayNode<T> pp = getPP(target, root);
    
    return null;
  }

  private SplayNode<T> getPP(SplayNode<T> target, SplayNode<T> node) {
    if(node == null) {
      return null;
    }
    int compareResult = target.element.compareTo(node.element);
    if(compareResult == 0) {
      return null;
    } else if(compareResult < 0) {
      if(node.left == null) {
        return null;
      }
      if(node.left.left == target || node.left.right == target) {
        return node;
      }
      return getPP(target, node.left);
    } else if(compareResult > 0) {
      if(node.right == null) {
        return null;
      }
      if(node.right.left == target || node.right.right == target) {
        return node;
      }
      return getPP(target, node.right);
    }
    return null;
  }

  private SplayNode<T> singleRotateL(SplayNode<T> k2) {
    return k2;
  }

  private SplayNode<T> singleRotateR(SplayNode<T> k2) {
    return k2;
  }

  private SplayNode<T> splayingLL(SplayNode<T> k3) {
    return k3;
  }

  private SplayNode<T> splayingLR(SplayNode<T> k3) {
    return k3;
  }

  private SplayNode<T> splayingRL(SplayNode<T> k3) {
    return k3;
  }

  private SplayNode<T> splayingRR(SplayNode<T> k3) {
    return k3;
  }

  public void printTree() {
    StringBuilder result = new StringBuilder();
    printTree(root, result, 0);
    result.append("\n");
    System.out.println(result);
  }

  private void printTree(SplayNode<T> t, StringBuilder result, int offset) {
    for(int i=0 ; i<offset ; i++)
      result.append(" ");
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

  private static class SplayNode<T> {
    SplayNode(T element) {
      this(element, null, null);
    }

    SplayNode(T element, SplayNode<T> left, SplayNode<T> right) {
      this.element = element;
      this.left = left;
      this.right = right;
    }

    T element;

    SplayNode<T> left;

    SplayNode<T> right;
  }

  private SplayNode<T> root;

  public static void main(String[] args) {
    SplayTree<Integer> tree = new SplayTree<>();
    int[] nums = new int[]{7,6,5,4,3,2,1};
    for(int num : nums) {
      tree.insert(num);
    }
    tree.printTree();
    
    int[] nums2 = new int[]{1,2,3,4,5,6,7};
    for(int num : nums2) {
      tree.find(num);
    }
  }

}