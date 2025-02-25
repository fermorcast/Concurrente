import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ColaConcurrente2 {

    private Nodo head;
    private Nodo tail;
    private ExecutorService executor;

    public ColaConcurrente2(int n_threads) {
        this.head = new Nodo("hnull");
        this.tail = new Nodo("tnull");
        this.head.next = null; // Cambiado a null para que funcione como referencia de vacía
        this.executor = Executors.newFixedThreadPool(n_threads);
    }

    public void enq(String x) {
        executor.execute(() -> {
            Nodo new_node = new Nodo(x);
            if (this.head.next == null) {
                new_node.next = this.tail;
                this.head.next = new_node;
            } else {
                Nodo last = this.tail.next;
                new_node.next = tail;
                last.next = new_node;
            }
            tail.next = new_node;
            System.out.println("Enqueued: " + x);
        });
    }

    public void deq() {
        executor.execute(() -> {
            if (this.head.next == null) {
                System.out.println("Queue is empty");
            } else {
                Nodo first = this.head.next;
                this.head.next = first.next;
                System.out.println("Dequeued: " + first.item);
            }
        });
    }

    public void print() {
        executor.execute(() -> {
            System.out.println("Printing queue");
            Nodo curr = this.head.next;
            while (curr != null && curr.item != "tnull") {
                System.out.println(curr.item);
                curr = curr.next;
            }
        });
    }

    public static void main(String[] args) {
        int n_hilos = 4;
        ColaConcurrente2 queue = new ColaConcurrente2(n_hilos);
        queue.enq("1");
        queue.enq("2");
        queue.enq("3");
        queue.deq(); // Debe desencolar "1"
        queue.enq("4");
        queue.deq(); // Debe desencolar "2"
        queue.deq(); // Debe desencolar "3"
        queue.deq(); // Debe desencolar "4"
    }
}

class Nodo {
    String item;
    Nodo next;

    public Nodo(String item) {
        this.item = item;
        this.next = null;
    }
}
