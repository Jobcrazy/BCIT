package ca.bcit.comp2522.assignment10.question4;

import java.io.PrintStream;
import java.util.Arrays;

public class Question4 {
    public void test() {
        System.out.println("-----Question 4-----");
        Website website = new Website(
                Arrays.asList(
                        new WebPage(
                                Arrays.asList(
                                        new Resource("Sample Content One"),
                                        new Resource("Some text")
                                )
                        ),
                        new WebPage(
                                Arrays.asList(
                                        new Resource("Text One"),
                                        new Resource("Text Two")
                                )
                        )
                )
        );

        website.getWebPages("Sample Content")
                .ifPresent(pages -> {
                    pages.forEach(page -> {
                        page.getResource().ifPresent(
                                resources -> {
                                    resources
                                            .forEach(
                                                    resource -> {
                                                        resource.getContent().ifPresent(
                                                                System.out::println
                                                        );
                                                    }
                                            );
                                }
                        );
                    });
                });
    }
}
