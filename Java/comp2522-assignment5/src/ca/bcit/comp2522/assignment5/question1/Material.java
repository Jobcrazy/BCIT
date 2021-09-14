package ca.bcit.comp2522.assignment5.question1;

public class Material {
    private final Integer materialCode;
    private final String materialName;

    public Material(Integer code, String name){
        materialCode = code;
        materialName = name;
    }

    public Integer getMaterialCode() {
        return materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }
}
