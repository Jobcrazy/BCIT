package com.corejsf;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named("customers")
@ApplicationScoped
public class CustomerBean implements Serializable {
    private ArrayList<Customer> customers;

    public CustomerBean() {
        customers = new ArrayList<>();

        customers.add(
                new Customer(
                        1,
                        "Hang",
                        "Liu",
                        "W",
                        "888",
                        "W 68TH",
                        "Vancouver",
                        "BC",
                        "V6P 2V1",
                        "888-888-8888",
                        "liuhang@bcit.com")
        );

        customers.add(
                new Customer(
                        2,
                        "James",
                        "Zhang",
                        "J",
                        "789",
                        "E 55TH",
                        "Vancouver",
                        "BC",
                        "V88 787",
                        "999-999-9999",
                        "james@bcit.com")
        );

        customers.add(
                new Customer(
                        3,
                        "Joon",
                        "Kim",
                        "K",
                        "789",
                        "N Earth",
                        "Soul",
                        "Hubei",
                        "430000",
                        "778-999-9999",
                        "joon@bcit.com")
        );
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public void delCustomer(Customer customer) {
        customers.remove(customer);
    }

    public String addCustomer(Customer customer) {
        customer.setId(customers.size() + 1);
        customers.add(customer);
        return "index";
    }

    public String save() {
        for (Customer customer : customers) {
            customer.setEditable(false);
        }
        return null;
    }
}
