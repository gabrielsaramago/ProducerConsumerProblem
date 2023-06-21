import javax.swing.plaf.IconUIResource;

public class Order implements Comparable<Order> {
    private static int cont = 0;
    private int id;
    private boolean orderReady = false;
    private boolean orderGettingCooked = false;


    public Order(){
        cont++;
        this.id=cont;
    }

    public int getId() {
        return id;
    }

    public boolean isOrderReady() {
        return orderReady;
    }

    public void SetOrderReady(boolean orderReady) {
        this.orderReady = orderReady;
    }

    public boolean isOrderGettingCooked() {
        return orderGettingCooked;
    }

    public void setOrderGettingCooked(boolean orderDelivered) {
        this.orderGettingCooked = orderDelivered;
    }

    @Override
    public String toString() {
        return "Order" +
                " id: " + id;};


    @Override
    public int compareTo(Order o) {
        if(id==o.getId()){
            return 0;
        }
        else if (id>o.getId()){
            return 1;
        }
        else{
            return -1;
        }

    }
}