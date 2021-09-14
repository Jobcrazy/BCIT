package ca.bcit.comp2522.assignment5.question0;

//No need to explicitly extends Class 'Product' from 'Object'
public class Product {
    private final Integer productID;
    private final String productName;
    private final Float productPrice;
    private final String productMadeInCountry;

    public Product(Integer id, String name, Float price, String country) {
        productID = id;
        productName = name;
        productPrice = price;
        productMadeInCountry = country;
    }

    public Integer getProductID() {
        return productID;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductMadeInCountry() {
        return productMadeInCountry;
    }

    public void printInfo() {
        System.out.printf("--------\n%s ID: %d\n%s Name: %s\n%s Price: %.2f\nMade in: %s\n",
                getClass().getSimpleName(),
                getProductID(),
                getClass().getSimpleName(),
                getProductName(),
                getClass().getSimpleName(),
                getProductPrice(),
                getProductMadeInCountry());
    }
}
