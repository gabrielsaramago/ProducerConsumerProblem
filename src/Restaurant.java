import java.util.PriorityQueue;
import java.util.Queue;

public class Restaurant {
    public static Queue<Order> ordersQueue;
    public static boolean isOpen;

    public static void main(String[] args) {
        isOpen = true;
        ordersQueue = new PriorityQueue<>();

        Thread client1 = new Thread(new Client());
        Thread client2 = new Thread(new Client());
        Thread client3 = new Thread(new Client());

        Thread cooker1 = new Thread(new Cooker());
        Thread cooker2 = new Thread(new Cooker());

        client1.start();
        client2.start();
        client3.start();

        cooker1.start();
        cooker2.start();

    }
}