package Storages;

import java.util.List;

import Models.Item;
import Models.Order;
import Models.OrderItem;

public interface OrderStorage {
    void add(Order order);
    Order findById(long id);
    List<Order> getAllOrders();
    List<Item> getItems(Order order);
    List<OrderItem> getOrderItems(Order order);
    void update(Order order);
    void delete(Order order);
}
