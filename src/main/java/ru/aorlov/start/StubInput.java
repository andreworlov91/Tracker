package ru.aorlov.start;

import java.util.List;

public class StubInput extends ValidateInput {
    private String[] answers;
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    public String ask(String question) {
        return answers[position++];
    }
}
