package com.kaka888.service;

import com.kaka888.access.SupplierManager;
import com.kaka888.model.Supplier;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;

@Path("/suppliers")
public class SupplierService {
    @Inject
    private SupplierManager supplierManager;

    @GET
    @Path("/")
    @Produces("application/xml")
    public ArrayList<Supplier> getAll() {
        return supplierManager.getAll();
    }

    @GET
    @Path("{id}")
    @Produces("application/xml")
    public Supplier getSupplier(@PathParam("id") int id) {
        ArrayList<Supplier> results = supplierManager.find(id);
        if (0!=results.size()){
            return results.get(0);
        }
        return null;
    }

    @DELETE
    @Path("{id}")
    public Response removeSupplier(@PathParam("id") int id) {
        supplierManager.remove(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("/add")
    @Consumes("application/xml")
    public Response add(Supplier supplier) {
        supplierManager.persist(supplier);
        return Response.created(URI.create("/suppliers/" + supplier.getId())).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/xml")
    public Response update(@PathParam("id") int id, Supplier supplier) {
        supplierManager.merge(supplier);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
