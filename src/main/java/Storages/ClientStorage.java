package Storages;

import java.util.List;
import Models.Client;
import Models.Order;

public interface ClientStorage {
    void add(Client client);
    Client findById(long id);
    List<Client> getAllClients();
    void update(Client client);
    void delete(Client client);
    List<Order> getOrders(Client client);
}
