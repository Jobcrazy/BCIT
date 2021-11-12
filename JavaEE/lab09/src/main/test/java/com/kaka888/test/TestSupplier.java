package com.kaka888.test;

import com.kaka888.model.Supplier;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;


/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class TestSupplier
{
    private static Client client;

    @BeforeClass
    public static void initClient()
    {
        client = ClientBuilder.newClient();
    }

    @AfterClass
    public static void closeClient()
    {
        client.close();
    }

    @Test
    public void testCustomerResource() throws Exception
    {
        System.out.println("*** Create a new Supplier ***");
        Supplier supplier = new Supplier();
        supplier.setName("Tik Tech");
        supplier.setContactName("James Liu");
        supplier.setContactTitle("Manager");
        supplier.setAddress("256 Clarendon Street");
        supplier.setCity("Boston");
        supplier.setStateOrProvince("MA");
        supplier.setPostalCode("02115");
        supplier.setCountry("USA");

        Response response = client.target("http://localhost:8080/h-liu-lab09/api/suppliers/add")
                .request().post(Entity.xml(supplier));
        if (response.getStatus() != 201) throw new RuntimeException("Failed to create");
        String location = response.getLocation().toString();
        System.out.println("Location: " + location);
        response.close();

        System.out.println("*** GET Created Supplier **");
        supplier = client.target(location).request().get(Supplier.class);
        System.out.println(supplier);

        supplier.setName("Hang Liu");
        response = client.target(location).request().put(Entity.xml(supplier));
        if (response.getStatus() != 204) throw new RuntimeException("Failed to update");

        // Show the update
        System.out.println("**** After Update ***");
        supplier = client.target(location).request().get(Supplier.class);
        System.out.println(supplier);

        // Delete the supplier
        System.out.println("**** Delete Supplier ***");
        response = client.target(location).request().delete();
        if (response.getStatus() != 204) throw new RuntimeException("Failed to delete");
        System.out.println("Deleted successfully!");
    }
}
