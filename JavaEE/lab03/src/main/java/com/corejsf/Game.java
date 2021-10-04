package com.corejsf;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("game")
@ConversationScoped
public class Game implements Serializable {
    private Integer numberOfTries;
    private Integer lowerBound;
    private Integer upperBound;
    private Integer answer;

    @Inject
    private Integer correctAnswer;

    @Inject
    private Conversation conversation;

    public Game() {
        resetGame();
    }

    private void resetGame() {
        numberOfTries = 0;
        lowerBound = 0;
        upperBound = 100;
    }

    public Integer getLowerBound() {
        return lowerBound;
    }

    public Integer getUpperBound() {
        return upperBound;
    }

    public Integer getNumberOfTries() {
        return numberOfTries;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public String checkAnswer() {
        if (numberOfTries == 0) {
            conversation.begin();
        }
        ++numberOfTries;
        if (answer.equals(correctAnswer)) {
            return "correct";
        } else if (answer < correctAnswer) {
            lowerBound = lowerBound > answer ? lowerBound : answer + 1;
            return "low";
        }
        upperBound = upperBound < answer ? upperBound : answer - 1;
        return "high";
    }

    public String startOver() {
        resetGame();
        conversation.end();
        return "index";
    }
}
