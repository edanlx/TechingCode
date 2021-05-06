package exam;

public class ReverseCode {
    static class Node {
        public int val;
        public Node next;

        Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        new ReverseCode().reverse(node1);
    }

    public Node reverse(Node root) {
        Node preNode = null;
        Node currentNode = root;
        while (currentNode != null) {
            Node temNode = currentNode;
            currentNode = currentNode.next;
            temNode.next = preNode;
            preNode = temNode;
        }
        return preNode;
    }
}
