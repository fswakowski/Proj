package Models;

import java.util.ArrayList;
import java.util.List;


public class Client {

    private long id;
    private String firstname;
    private String lastname;
    private String mail;
    private List<Order> orders = new ArrayList<Order>();

    public Client(){
        super();
    }

    public Client(long id, String firstname, String lastname, String mail)
    {
        super();
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
    }

    public Client(long id, String firstname, String lastname,String mail, List<Order> orders) {
        super();
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.orders = orders;
    }


    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setClient(this);
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
