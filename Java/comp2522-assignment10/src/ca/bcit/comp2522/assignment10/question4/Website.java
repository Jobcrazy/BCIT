package ca.bcit.comp2522.assignment10.question4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Website {
    List<WebPage> webPages;

    public Website(List<WebPage> p) {
        webPages = p;
    }

    Optional<List<WebPage>> getWebPages(String content) {
        ArrayList<WebPage> results = new ArrayList<>();

        if (null != webPages) {
            webPages.forEach(
                    page -> {
                        if (page.getResource().isPresent()) {
                            page.getResource().get().forEach(
                                    resource -> {
                                        if (resource.getContent().isPresent() &&
                                                resource.getContent().get().contains(content)) {
                                            results.add(page);
                                        }
                                    }
                            );
                        }
                    }
            );
        }

        return Optional.ofNullable(results);
    }
}
