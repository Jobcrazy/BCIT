package com.kaka888.access;

import com.kaka888.model.Supplier;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

@Named
@SessionScoped
public class SupplierManager implements Serializable {
    @Resource(mappedName = "java:jboss/datasources/inventory")
    private DataSource dataSource;

    private String keyWord;
    private Supplier supplierToAdd = new Supplier();
    private Supplier supplierToEdit;
    private final ArrayList<Supplier> searchResult = new ArrayList<>();

    public String getKeyWord() {
        return keyWord;
    }

    public Supplier getSupplierToEdit() {
        return supplierToEdit;
    }

    public void setSupplierToEdit(Supplier supplierToEdit) {
        this.supplierToEdit = supplierToEdit;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public ArrayList<Supplier> getSearchResult() {
        return searchResult;
    }

    public ArrayList<Supplier> getSuppliers() {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet result = statement.executeQuery(
                        "SELECT * FROM Suppliers");
                while (result.next()) {
                    suppliers.add(new Supplier(
                            result.getInt("SupplierID"),
                            result.getString("SupplierName"),
                            result.getString("ContactName"),
                            result.getString("ContactTitle"),
                            result.getString("Address"),
                            result.getString("City"),
                            result.getString("PostalCode"),
                            result.getString("StateOrProvince"),
                            result.getString("Country"),
                            result.getString("PhoneNumber"),
                            result.getString("FaxNumber"),
                            result.getString("paymentTerms"),
                            result.getString("EmailAddress"),
                            result.getString("Notes")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return suppliers;
    }

    public String delete(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM Suppliers WHERE SupplierID = ?")) {
                statement.setInt(1, supplier.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public String edit(Supplier supplier) {
        supplierToEdit = supplier;
        return "edit";
    }

    public String search() {
        try (Connection conn = dataSource.getConnection()) {
            searchResult.clear();
            try (PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM Suppliers WHERE SupplierName like '%"
                            + keyWord + "%'")
            ) {
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    searchResult.add(new Supplier(
                            result.getInt("SupplierID"),
                            result.getString("SupplierName"),
                            result.getString("ContactName"),
                            result.getString("ContactTitle"),
                            result.getString("Address"),
                            result.getString("City"),
                            result.getString("PostalCode"),
                            result.getString("StateOrProvince"),
                            result.getString("Country"),
                            result.getString("PhoneNumber"),
                            result.getString("FaxNumber"),
                            result.getString("paymentTerms"),
                            result.getString("EmailAddress"),
                            result.getString("Notes")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return "search";
    }

    public String addSupplier(){
        supplierToAdd = new Supplier();
        return "add";
    }

    public Supplier getSupplierToAdd() {
        return supplierToAdd;
    }

    public void setSupplierToAdd(Supplier supplierToAdd) {
        this.supplierToAdd = supplierToAdd;
    }

    public String persist(){
        //order of fields in INSERT statement
        final int supplierID = 1;
        final int supplierName = 2;
        final int contactName = 3;
        final int contactTitle = 4;
        final int address = 5;
        final int city = 6;
        final int postalCode = 7;
        final int stateOrProvince = 8;
        final int country = 9;
        final int phoneNumber = 10;
        final int faxNumber = 11;
        final int paymentTerms = 12;
        final int emailAddress = 13;
        final int notes = 14;
        try {
            try (Connection connection = dataSource.getConnection()) {
                try (PreparedStatement stmt = connection.prepareStatement(
                        "INSERT INTO Suppliers "
                                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    stmt.setInt(supplierID, supplierToAdd.getId());
                    stmt.setString(supplierName, supplierToAdd.getName());
                    stmt.setString(contactName, supplierToAdd.getContactName());
                    stmt.setString(contactTitle, supplierToAdd.getContactTitle());
                    stmt.setString(address, supplierToAdd.getAddress());
                    stmt.setString(city, supplierToAdd.getCity());
                    stmt.setString(postalCode, supplierToAdd.getPostalCode());
                    stmt.setString(stateOrProvince,
                            supplierToAdd.getStateOrProvince());
                    stmt.setString(country, supplierToAdd.getCountry());
                    stmt.setString(phoneNumber, supplierToAdd.getPhoneNumber());
                    stmt.setString(faxNumber, supplierToAdd.getFaxNumber());
                    stmt.setString(paymentTerms, supplierToAdd.getPaymentTerms());
                    stmt.setString(emailAddress, supplierToAdd.getEmailAddress());
                    stmt.setString(notes, supplierToAdd.getNotes());
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return "index";
    }

    public String update() {
        //order of fields in UPDATE statement
        final int supplierName = 1;
        final int contactName = 2;
        final int contactTitle = 3;
        final int address = 4;
        final int city = 5;
        final int postalCode = 6;
        final int stateOrProvince = 7;
        final int country = 8;
        final int phoneNumber = 9;
        final int faxNumber = 10;
        final int paymentTerms = 11;
        final int emailAddress = 12;
        final int notes = 13;
        final int supplierID = 14;
        try {
            try (Connection connection = dataSource.getConnection()) {
                try (PreparedStatement stmt = connection.prepareStatement("UPDATE Suppliers "
                        + "SET SupplierName = ?, ContactName = ?, "
                        + "ContactTitle = ?, Address = ?, City = ?, "
                        + "PostalCode = ?, StateOrProvince = ?, "
                        + "Country = ?, PhoneNumber = ?, FaxNumber = ?, "
                        + "PaymentTerms = ?, EmailAddress = ?, "
                        + "Notes = ? " + "WHERE SupplierID =  ?")) {
                    stmt.setString(supplierName, supplierToEdit.getName());
                    stmt.setString(contactName, supplierToEdit.getContactName());
                    stmt.setString(contactTitle, supplierToEdit.getContactTitle());
                    stmt.setString(address, supplierToEdit.getAddress());
                    stmt.setString(city, supplierToEdit.getCity());
                    stmt.setString(postalCode, supplierToEdit.getPostalCode());
                    stmt.setString(stateOrProvince,
                            supplierToEdit.getStateOrProvince());
                    stmt.setString(country, supplierToEdit.getCountry());
                    stmt.setString(phoneNumber, supplierToEdit.getPhoneNumber());
                    stmt.setString(faxNumber, supplierToEdit.getFaxNumber());
                    stmt.setString(paymentTerms, supplierToEdit.getPaymentTerms());
                    stmt.setString(emailAddress, supplierToEdit.getEmailAddress());
                    stmt.setString(notes, supplierToEdit.getNotes());
                    stmt.setInt(supplierID, supplierToEdit.getId());
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return "index";
    }
}
