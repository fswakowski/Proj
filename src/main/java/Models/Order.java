package Models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private long id;
    private Client client;
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    public Order()
    {
        super();
    }

    public Order(long id, Client client)
    {
        super();
        this.id = id;
        this.client = client;
    }

    public Order(long id, Client client, List<OrderItem> items) {
        super();
        this.id = id;
        this.client = client;
        this.orderItems = items;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }
}
