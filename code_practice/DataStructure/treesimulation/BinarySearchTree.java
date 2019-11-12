/**
 * InnerBinarySearchTree
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    
    public BinarySearchTree() {
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

    public T findMin() {
        if(isEmpty())
            throw new RuntimeException();
        return findMin(root).element;
    }

    public T findMax() {
        if(isEmpty())
            throw new RuntimeException();
        return findMax(root).element;
    }

    public void insert(T item) {
        root = insert(item, root);
    }

    public void remove(T item) {
        root = remove(item, root);
    }

    public void printTree() {
        StringBuilder result = new StringBuilder();
        printTree(root, result, 0);
        result.append("\n");
        System.out.println(result);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private boolean contains(T item, BinaryNode<T> t) {
        if(t == null)
            return false;
        int compareResult = item.compareTo(t.element);
        if(compareResult < 0)
            return contains(item, t.left);
        else if(compareResult > 0)
            return contains(item, t.right);
        else
            return true;
    }

    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if(t == null)
            return null;
        if(t.left == null)
            return t;
        return
            findMin(t.left);
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if(t == null)
            return null;
        if(t.right == null)
            return t;
        return
            findMax(t.right);
    }

    private BinaryNode<T> insert(T item, BinaryNode<T> t) {
        if(t == null)
            return new BinaryNode<>(item, null, null);
        int compareResult = item.compareTo(t.element);
        if(compareResult < 0)
            t.left = insert(item, t.left);
        if(compareResult > 0)
            t.right = insert(item, t.right);
        return t;
    }

    private BinaryNode<T> remove(T item, BinaryNode<T> t) {
        if(t == null)
            return t;
        int compareResult = item.compareTo(t.element);
        if(compareResult < 0)
            t.left = remove(item, t.left);
        else if(compareResult > 0)
            t.right = remove(item, t.right);
        else if(t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    protected static class BinaryNode<T> {
        BinaryNode(T element) {
            this(element, null, null);
        }

        BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        T element;

        BinaryNode<T> left;

        BinaryNode<T> right;
    }

    private void printTree(BinaryNode<T> t, StringBuilder result, int offset) {
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

    private BinaryNode<T> root;

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int[] sequence = new int[]{8,4,12,2,6,10,14,1,3,5,7,9,11,13,15};
        for(int i : sequence)
            tree.insertAndPrint(i);

        sequence = new int[]{1,2,10,15,12};
        for(int i : sequence)
            tree.removeAndPrint(i);
        
    }

    public void insertAndPrint(T item) {
        insert(item);
        System.out.println("insert: " + item);
        printTree();
        System.out.println("min: " + findMin());
        System.out.println("max: " + findMax());
        System.out.println();
    }

    public void removeAndPrint(T item) {
        remove(item);
        System.out.println("remove: " + item);
        printTree();
        System.out.println("min: " + findMin());
        System.out.println("max: " + findMax());
        System.out.println();
    }
}