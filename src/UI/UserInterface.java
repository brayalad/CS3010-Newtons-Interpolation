package UI;

import Models.Evaluation;

public interface UserInterface {

    String getMenuOption();

    String getInput();

    String getFilePath();

    String getTestValue();

    long getNumSize();

    void printResults(Evaluation evaluation);

    void invalidInput(String input);

}
