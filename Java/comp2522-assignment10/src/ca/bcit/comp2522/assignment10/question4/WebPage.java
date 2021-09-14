package ca.bcit.comp2522.assignment10.question4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WebPage {
    List<Resource> resources;

    public  WebPage(List<Resource> r){
        resources = r;
    }

    public Optional<List<Resource>> getResource(){
        return Optional.ofNullable(resources);
    }
}
