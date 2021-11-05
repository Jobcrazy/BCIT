package com.kaka888.controller;

import com.kaka888.access.SupplierManager;
import com.kaka888.model.Supplier;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;

@Named
@SessionScoped
public class SupplierController implements Serializable {
    @Inject
    SupplierManager supplierManager;

    private String keyWord;
    private Supplier supplierToAdd;
    private Supplier supplierToEdit;
    private ArrayList<Supplier> searchResults;

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
            getExternalContext().getSession(true);

    public SupplierController(){
        supplierToAdd = new Supplier();
        searchResults = new ArrayList<>();
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public ArrayList<Supplier> getSearchResults() {
        return searchResults;
    }

    public SupplierManager getSupplierManager() {
        return supplierManager;
    }

    public Supplier getSupplierToAdd() {
        return supplierToAdd;
    }

    public void setSupplierToAdd(Supplier supplierToAdd) {
        this.supplierToAdd = supplierToAdd;
    }

    public Supplier getSupplierToEdit() {
        return supplierToEdit;
    }

    public void setSupplierToEdit(Supplier supplierToEdit) {
        this.supplierToEdit = supplierToEdit;
    }

    public String search(){
        searchResults = supplierManager.getSearch(keyWord);
        return "search";
    }

    public String addSupplier(){
        return "add";
    }

    public String editSupplier(Supplier supplier){
        supplierToEdit = supplier;
        return "modify";
    }

    public String deleteSupplier(Supplier supplier){
        supplierManager.remove(supplier);
        return null;
    }

    public String persistSupplier() {
        supplierManager.persist(supplierToAdd);
        session.invalidate();
        return "index";
    }

    public String updateSupplier(){
        supplierManager.merge(supplierToEdit);
        session.invalidate();
        return "index";
    }
}
