package ca.bcit.comp2522.assignment10.question4;

import java.util.Optional;

public class Resource {
    String content;

    public Resource(String c) {
        content = c;
    }

    public Optional<String> getContent(){
        return Optional.ofNullable(content);
    }
}
