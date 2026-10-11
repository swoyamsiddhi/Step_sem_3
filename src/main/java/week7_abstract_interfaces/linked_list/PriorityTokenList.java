package week7_abstract_interfaces.linked_list;

/**
 * Problem 1 – Priority Token Insertion
 *
 * Custom singly linked list — does NOT use java.util.LinkedList.
 *
 * Time Complexities:
 *   addFirst(token)           : O(1)  – update head pointer
 *   addLast(token)            : O(n)  – must traverse to the tail
 *   insertAt(index, token)    : O(n)  – must walk to index-1 node
 */
public class PriorityTokenList {

    // ── Node ─────────────────────────────────────────────────────────────────
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // ── Singly Linked List ────────────────────────────────────────────────────
    static class TokenList {
        private Node head;

        /** Inserts at the front – O(1). */
        public void addFirst(int token) {
            Node newNode = new Node(token);
            newNode.next = head;
            head = newNode;
        }

        /** Inserts at the end – O(n). */
        public void addLast(int token) {
            Node newNode = new Node(token);
            if (head == null) { head = newNode; return; }
            Node curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = newNode;
        }

        /**
         * Inserts at 0-based index – O(n).
         * @param index must be in [0, current size]
         */
        public void insertAt(int index, int token) {
            if (index == 0) { addFirst(token); return; }
            Node curr = head;
            for (int i = 0; i < index - 1; i++) {
                if (curr == null) throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
                curr = curr.next;
            }
            Node newNode = new Node(token);
            newNode.next = curr.next;  // new node points to the old successor
            curr.next = newNode;       // predecessor now points to new node
        }

        /** Builds the human-readable list string, e.g. 100 -> 101 -> null */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node curr = head;
            while (curr != null) {
                sb.append(curr.data).append(" -> ");
                curr = curr.next;
            }
            sb.append("null");
            return sb.toString();
        }

        /** Helper to build a list from an array. */
        static TokenList of(int... values) {
            TokenList list = new TokenList();
            for (int v : values) list.addLast(v);
            return list;
        }
    }

    // ── Main ──────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("--- Linked List Problem 1: Priority Token Insertion ---");

        // start = 101 -> 102 -> 103
        TokenList list = TokenList.of(101, 102, 103);
        System.out.println("Initial : " + list);

        list.addFirst(100);           // 100 -> 101 -> 102 -> 103
        System.out.println("addFirst(100) : " + list);

        list.addLast(104);            // 100 -> 101 -> 102 -> 103 -> 104
        System.out.println("addLast(104)  : " + list);

        list.insertAt(2, 150);        // 100 -> 101 -> 150 -> 102 -> 103 -> 104
        System.out.println("insertAt(2, 150): " + list);
        // Expected: 100 -> 101 -> 150 -> 102 -> 103 -> 104 -> null
    }
}
