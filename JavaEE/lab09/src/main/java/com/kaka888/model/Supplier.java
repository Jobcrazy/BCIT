package com.kaka888.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Entity
@Table(name = "Suppliers")
@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class Supplier implements Serializable {
    @Id
    @Column(name = "SupplierID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute
    private int id;

    @Column(name = "SupplierName")
    @XmlElement
    private String name;

    @Column(name = "ContactName")
    @XmlElement
    private String contactName;

    @Column(name = "ContactTitle")
    @XmlElement
    private String contactTitle;

    @Column(name = "Address")
    @XmlElement
    private String address;

    @Column(name = "City")
    @XmlElement
    private String city;

    @Column(name = "PostalCode")
    @XmlElement
    private String postalCode;

    @Column(name = "StateOrProvince")
    @XmlElement
    private String stateOrProvince;

    @Column(name = "Country")
    @XmlElement
    private String country;

    @Column(name = "PhoneNumber")
    @XmlElement
    private String phoneNumber;

    @Column(name = "FaxNumber")
    @XmlElement
    private String faxNumber;

    @Column(name = "PaymentTerms")
    @XmlElement
    private String paymentTerms;

    @Column(name = "EmailAddress")
    @XmlElement
    private String emailAddress;

    @Column(name = "Notes")
    @XmlElement
    private String notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
