package UI;

import Models.Evaluation;

import java.util.Scanner;

public class TextUserInterface implements UserInterface {
    private static final String ENTER_FILE = "Enter path to file: ";
    private static final String ENTER_TEST_VALUE = "Enter value to test against (q to exit)t: ";
    private static final String ENTER_NUM_SIZE = "Enter number of random points: ";
    private static final String EVALUATION_RESULTS_FORMAT = "\nEvaluation Results:\n%s";
    private static final String MENU = "Program Options:\n 1) Read from file.\n 2) Use random points.\n q) Quit Program\n";
    private static final String ENTER_OPTION = "Enter Option: ";
    private static final String INVALID_INPUT_FORMAT = "[%s] is not a valid input.";

    private final Scanner input;

    public TextUserInterface(final Scanner input){
        this.input = input;
    }

    @Override
    public String getMenuOption(){
        print(MENU);
        print(ENTER_OPTION);
        return getInput();
    }

    @Override
    public String getInput(){
        return input.nextLine();
    }

    @Override
    public String getFilePath(){
        print(ENTER_FILE);
        return getInput();
    }

    @Override
    public String getTestValue(){
        print(ENTER_TEST_VALUE);
        return getInput();
    }

    @Override
    public long getNumSize(){
        print(ENTER_NUM_SIZE);
        return Long.parseLong(getInput());
    }

    @Override
    public void printResults(final Evaluation evaluation){
        println(String.format(EVALUATION_RESULTS_FORMAT, evaluation));
    }

    @Override
    public void invalidInput(final String input){
        println(String.format(INVALID_INPUT_FORMAT, input));
    }

    private void print(final String s){
        System.out.print(s);
    }

    private void println(final String s){
        System.out.println(s);
    }



}
