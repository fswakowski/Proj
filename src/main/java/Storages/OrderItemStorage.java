package Storages;

import java.util.List;

import Models.OrderItem;

public interface OrderItemStorage {
    void add(OrderItem orderItem);
    void delete(OrderItem orderItem);
    void update(OrderItem orderItem);
    List<OrderItem> getAllOrderItems();
    OrderItem findById(long id);
}
