package com.kaka888.access;

import com.kaka888.model.Supplier;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;

@Dependent
@Stateless
public class SupplierManager implements Serializable {
    @PersistenceContext(unitName = "inventory-jpa")
    EntityManager em;

    public ArrayList<Supplier> getAll() {
        TypedQuery<Supplier> query = em.createQuery(
                "SELECT s FROM Supplier s", Supplier.class);
        return new ArrayList<>(query.getResultList());
    }

    public ArrayList<Supplier> find(int id) {
        TypedQuery<Supplier> query = em.createQuery(
                "SELECT s FROM Supplier s WHERE s.id = :id", Supplier.class);
        query.setParameter("id", id);
        return new ArrayList<>(query.getResultList());
    }

    public void persist(Supplier supplier) {
        em.persist(supplier);
    }

    public void merge(Supplier supplier) {
        em.merge(supplier);
    }

    public void remove(Supplier supplier){
        supplier = em.find(Supplier.class, supplier.getId());
        em.remove(supplier);
    }

    public void remove(int id){
        Supplier supplier = em.find(Supplier.class, id);
        em.remove(supplier);
    }

    public ArrayList<Supplier> getSearch(String keyWord) {
        TypedQuery<Supplier> query = em.createQuery(
                "SELECT s FROM Supplier s WHERE s.contactName LIKE '%"
                        + keyWord + "%'", Supplier.class);
        return new ArrayList<>(query.getResultList());
    }
}
