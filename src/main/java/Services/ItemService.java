package Services;

import java.util.List;

import Models.Item;
import Models.Order;

public interface ItemService {
    void add(Item item);
    void update(Item item);
    void delete(Item item);
    Item findById(Long id);
    List<Item> getAllItems();
    List<Order> getItemOrders(Item item);
    boolean itemExists(Item item);
}
