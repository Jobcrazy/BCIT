package ca.bcit.comp2522.assignment5.question1;

import ca.bcit.comp2522.assignment5.question0.Product;

public class Cloth extends Product {
    Material[] clothMaterials;

    public Cloth(Integer id, String name, Float price, String country, Material[] materials) {
        super(id, name, price, country);
        clothMaterials = materials;
    }

    public Material[] getClothMaterials() {
        return clothMaterials;
    }

    @Override
    public void printInfo() {
        super.printInfo();

        System.out.print("Materials: ");

        for (int index = 0; index < clothMaterials.length; index++) {
            if (clothMaterials.length - 1 != index) {
                System.out.printf("%s, ", clothMaterials[index].getMaterialName());
            } else {
                System.out.printf("%s", clothMaterials[index].getMaterialName());
            }
        }

        System.out.println("");
    }
}
