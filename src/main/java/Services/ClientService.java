package Services;

import java.util.List;

import Models.Client;
import Models.Order;

public interface ClientService {
    void add(Client client);
    void update(Client client);
    void delete(Client client);
    List<Client> getAllClients();
    List<Order> getClientOrders(Client client);
    Client findById(Long id);
    boolean clientExists(Client client);
}
