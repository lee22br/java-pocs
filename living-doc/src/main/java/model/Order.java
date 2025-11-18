package model;

public class Order {
    private int id;
    private Client client;
    private double totalValue;

    public Order(int id, Client client, double totalValue) {
        this.id = id;
        this.client = client;
        this.totalValue = totalValue;
    }

    public int getId() {

        return id;
    }

    public Client getClient() {
        return client;
    }

    public double getTotalValue() {
        return totalValue;
    }
}
