package com.corejsf;

import javax.enterprise.inject.Produces;
import java.util.Random;

public class AnswerProducer {
    private Random random = new Random(System.currentTimeMillis());

    @Produces
    Integer generateAnswer(){
        return random.nextInt(100);
    }
}
