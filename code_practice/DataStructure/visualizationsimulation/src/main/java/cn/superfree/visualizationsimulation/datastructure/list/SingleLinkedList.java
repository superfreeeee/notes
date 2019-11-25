package cn.superfree.visualizationsimulation.datastructure.list;

public class SingleLinkedList extends MyList {

    public SingleLinkedList () {}

    @Override
    public int insert (int data) {
        return insert(length(), data);
    }

    @Override
    public int insert (int index, int data) {
        if(index < 0 || index > length()) {
            return -1;
        }
        if(head == null) {
            head = new Node(data);
            return 0;
        }
        Node current = head;
        int offset = index - 1;
        while(offset > 0) {
            current = current.next;
            offset -= 1;
        }
        Node n = new Node(data);
        n.next = current.next;
        current.next = n;
        return index;
    }

    @Override
    public int length () {
        int len = 0;
        Node current = head;
        while(current != null) {
            len++;
            current = current.next;
        }
        return len;
    }

    @Override
    public int[] toArray() {
        int[] result = new int[length()];
        int i = 0;
        for(Node current=head ; current != null ; current = current.next) {
            result[i++] = current.data;
        }
        return result;
    }

    @Override
    public void reset() {
        head = null;
    }

    private static class Node {

        int data;

        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        for(int i=1 ; i<100 ; i*=2) {
            singleLinkedList.insert(i);
        }
        System.out.println(singleLinkedList);
    }

}
