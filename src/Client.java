import java.util.Iterator;

public class Client implements Runnable {

    private int id;
    private static int cont = 0;
    private int orderIDClient;
    private int waitingPeriod = 5000;
    private boolean waitingForOrder = false;
    private final String name;

    public Client() {
        cont++;
        this.id=cont;
        name = "Client " + id;
    }

    @Override
    public void run() {
        while (Restaurant.isOpen){
            //make the order if the client is not waiting for an order
            if(!waitingForOrder){
                order();
                waitingForOrder = true;
            }
            //search in the queue if the order is ready
            else{
                searchOrder();
            }
        }
    }

    public void order(){
        Order myOrder = new Order();
        this.orderIDClient = myOrder.getId();
        synchronized (Restaurant.ordersQueue){
            Restaurant.ordersQueue.add(myOrder);
        }
        System.out.println(name + " asked " + myOrder);
    }

    public void searchOrder(){
        synchronized (Restaurant.ordersQueue){
            Iterator<Order> value = Restaurant.ordersQueue.iterator();
            while(value.hasNext()){
                Order order = value.next();
                if(order.getId() == orderIDClient && order.isOrderReady()){
                    waitingForOrder = false;
                    System.out.println(name + " picked " + order);
                    try {
                        Thread.sleep(waitingPeriod);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }


    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Client{" +
                " id = " + id +
                '}';
    }
}