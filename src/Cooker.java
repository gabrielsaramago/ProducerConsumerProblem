import java.util.Iterator;

public class Cooker implements Runnable{

    private int id;
    private static int count = 0;
    private boolean isCooking = false;
    private int timeToCook = 1000;

    private String name;

    Order orderCooking;


    public Cooker() {
        count++;
        this.id = count;
        name = "Cooker " + id;
    }

    @Override
    public void run() {
        while(Restaurant.isOpen){
            if(!isCooking){
                searchOrder();
            }
            else{
                prepareOrder(orderCooking);
            }
        }
    }

    public void searchOrder(){
        //search a order that is not getting ready yet
        synchronized (Restaurant.ordersQueue){
            Iterator<Order> value = Restaurant.ordersQueue.iterator();
            while(value.hasNext() && !isCooking){
                Order order = value.next();
                if(!order.isOrderGettingCooked()){
                    order.setOrderGettingCooked(true);
                    System.out.println(name + " is cooking " + order);
                    isCooking = true;
                    orderCooking = order;
                    return;
                }
            }
        }
    }

    public void prepareOrder(Order order){
        try {
            Thread.sleep(timeToCook);
            synchronized (Restaurant.ordersQueue){
                order.SetOrderReady(true);
            }
            isCooking = false;
            System.out.println(name + " is delivering " + order);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}