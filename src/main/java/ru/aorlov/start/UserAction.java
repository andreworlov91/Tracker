package ru.aorlov.start;

public interface UserAction {

    int key();

    void execute(Input input, Tracker tracker);

    String info();
}
