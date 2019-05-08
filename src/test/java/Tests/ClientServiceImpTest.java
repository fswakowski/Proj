package Tests;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import Services.ClientServiceImpl;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import Mocks.MockClientStorage;
import Mocks.MockOrderItemStorage;
import Mocks.MockOrderStorage;
import Models.Client;
import Models.Order;
import Services.ClientService;
import Services.OrderService;
import Services.OrderServiceImpl;
import Storages.ClientStorage;
import Storages.OrderItemStorage;
import Storages.OrderStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.catchThrowable;


public class ClientServiceImpTest {

    ClientService clientService;
    ClientStorage clientStorage;
    OrderService orderService;
    OrderStorage orderStorage;
    OrderItemStorage orderItemStorage;
    Client client;

    @BeforeEach
    public void setUp() {
        clientStorage = new MockClientStorage();
        orderStorage = new MockOrderStorage();
        orderItemStorage = new MockOrderItemStorage();
        orderService = OrderServiceImpl.createWith(orderStorage, orderItemStorage);
        clientService = ClientServiceImpl.createWith(clientStorage, orderService);
        client = new Client(1, "Fabian", "Swakowski", "fs@test.com");
    }

    @Test
    public void addWithClientArgAddsClient() {
        clientService.add(client);

        Client result = clientStorage.findById(1);

        assertEquals(client, result);
    }

    @Test
    public void addWithExistingClientThrowsException() {
        clientService.add(client);

        //Second time
        Throwable thrown;
        thrown = catchThrowable(() -> clientService.add(client));
        assertThat(thrown).hasMessageContaining("Client exists!");
    }


    @Test
    public void addWithNullArgThrowsException() {
        Throwable thrown;
        thrown = catchThrowable(() ->  clientService.add(null));
        assertThat(thrown).hasMessageContaining("Client cannot be null!");
    }

    @Test
    public void updateWithClientArgUpdatesClient() {
        clientStorage.add(client);
        client.setFirstname("Adam");

        clientService.update(client);

        String expected = "Adam";
        String result = clientStorage.findById(1).getFirstname();

        assertEquals(expected, result);
    }

    @Test
    public void updateWithNullArgThrowsException() {
        clientStorage.add(client);

        Throwable thrown;
        thrown = catchThrowable(() -> clientService.update(null));
        assertThat(thrown).hasMessageContaining("Client cannot be null!");
    }

    @Test
    public void updateWhenClientDoesntExistThrowsException() {
        Throwable thrown;
        thrown = catchThrowable(() -> clientService.update(client));
        assertThat(thrown).hasMessageContaining("Client does not exist!");
    }

    @Test
    public void deleteWithClientArgDeletesClient() {
        clientStorage.add(client);

        clientService.delete(client);

        boolean result = clientStorage.getAllClients().isEmpty();

        assertTrue(result);
    }

    @Test
    public void deleteWithNullArgThrowsException() {
        Throwable thrown = catchThrowable(() -> { clientService.delete(null); });
        assertThat(thrown).hasMessageContaining("Client cannot be null!");
    }

    @Test
    public void deleteWhenClientDoesntExistThrowsException() {
        Throwable thrown;
        thrown = catchThrowable(() ->  clientService.delete(client));
        assertThat(thrown).hasMessageContaining("Client does not exist!");
    }

    @Test
    public void deleteRemovesAssociatedOrders() {
        Order order = new Order();
        client.addOrder(order);
        clientStorage.add(client);
        orderStorage.add(order);

        clientService.delete(client);

        List<Order> result = orderStorage.getAllOrders();

        assertThat(result).isEqualTo(new ArrayList<Order>());
    }

    @Test
    public void getAllClientsReturnsClients() {
        clientStorage.add(client);

        List<Client> result = clientService.getAllClients();

        assertThat(result, hasSize(1));
    }

    @Test
    public void getClientOrdersWithClientArgReturnsOrders() {
        Order order = new Order();
        client.addOrder(order);
        clientStorage.add(client);
        orderStorage.add(order);

        List<Order> result = clientService.getClientOrders(client);

        assertThat(result, hasSize(1));
    }

    @Test
    public void getClientOrdersWithNullArgThrowsException() {
        Throwable thrown;
        thrown = catchThrowable(() -> clientService.getClientOrders(null));
        assertThat(thrown).hasMessageContaining("Client cannot be null!");
    }

    @Test
    public void getClientOrdersWhenClientDoesntExistThrowsException() {
        Throwable thrown;
        thrown = catchThrowable(() -> clientService.getClientOrders(client));
        assertThat(thrown).hasMessageContaining("Client does not exist!");
    }

    @Test
    public void findByIdReturnsClient() {
        clientStorage.add(client);

        Client result = clientService.findById(client.getId());

        assertEquals(client, result);
    }

    @Test
    public void findByIdWithArgNullThrowsException() {
        Throwable thrown;
        thrown = catchThrowable(() -> clientService.findById(null));
        assertThat(thrown).hasMessageContaining("ID cannot be null!");
    }

    @Test
    public void clientExistsWithArgNullThrowsException() {
        Throwable thrown;
        thrown = catchThrowable(() -> clientService.clientExists(null));
        assertThat(thrown).hasMessageContaining("Client cannot be null!");
    }


    @Test
    public void clientExistsWhenClientExistReturnsTrue() {
        clientStorage.add(client);

        boolean result = clientService.clientExists(client);

        assertTrue(result);
    }

    @Test
    public void clientExistsWhenClientDoesntExistReturnsFalse() {
        boolean result = clientService.clientExists(client);

        assertFalse(result);
    }

    @AfterEach
    public void clean() {
        clientStorage = null;
        orderStorage = null;
        orderItemStorage = null;
        orderService = null;
        clientService = null;
        client = null;
    }

}
