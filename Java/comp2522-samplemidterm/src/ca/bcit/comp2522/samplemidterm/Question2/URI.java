package ca.bcit.comp2522.samplemidterm.Question2;

public class URI {
    private final String protocol;
    private final String baseURL;
    private final String resourceAddress;

    public URI(String protocol,
               String baseURL,
               String resourceAddress){
        this.protocol = protocol;
        this.baseURL = baseURL;
        this.resourceAddress = resourceAddress;
    }

    void printURI(){
        System.out.printf("%s://%s/%s\n", protocol, baseURL, resourceAddress);
    }
}
