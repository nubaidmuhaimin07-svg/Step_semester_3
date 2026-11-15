public class Q4TrainCoachesBothWays {
    static class Node {
        String name;
        Node prev, next;
        Node(String name) { this.name = name; }
    }

    static class Train {
        Node head, tail;

        void addLast(String name) {
            Node n = new Node(name);
            if (head == null) head = tail = n;
            else {
                tail.next = n;
                n.prev = tail;
                tail = n;
            }
        }

        void remove(String name) {
            Node current = head;
            while (current != null && !current.name.equals(name)) current = current.next;
            if (current == null) return;
            if (current.prev != null) current.prev.next = current.next;
            else head = current.next;
            if (current.next != null) current.next.prev = current.prev;
            else tail = current.prev;
        }

        void printForward() {
            System.out.print("Forward: ");
            Node current = head;
            while (current != null) {
                System.out.print(current.name);
                if (current.next != null) System.out.print(" <-> ");
                current = current.next;
            }
            System.out.println();
        }

        void printBackward() {
            System.out.print("Backward: ");
            Node current = tail;
            while (current != null) {
                System.out.print(current.name);
                if (current.prev != null) System.out.print(" <-> ");
                current = current.prev;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Train train = new Train();
        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");
        train.remove("B1");
        train.printForward();
        train.printBackward();
    }
}
