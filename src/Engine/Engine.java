package Engine;

import Algorithms.NewtonsInterpolation;
import Models.EvalRequest;
import Models.Evaluation;
import UI.UserInterface;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Engine {
    private static final String QUIT_OPTION = "q";
    private static final double RAND_UPPER_LIMIT = 100.0;
    private static final double RAND_LOWER_LIMIT = -100.0;
    private static final Random RAND = new Random();

    private final UserInterface ui;


    public Engine(final UserInterface ui){
        this.ui = ui;
    }

    public void run() throws IOException {
        String input = "";

        while(!input.equals(QUIT_OPTION)){
            input = ui.getMenuOption();
            if(input.equals("1")){
                ui.printResults(evaluate(EvalType.FILE));
            } else if(input.equals("2")){
                ui.printResults(evaluate(EvalType.RANDOM));
            } else {
                if(!input.equals(QUIT_OPTION))
                    ui.invalidInput(input);
            }
        }

        System.exit(0);
    }

    private Evaluation evaluate(final EvalType evalType) throws IOException {
        final EvalRequest.Builder request = EvalRequest.builder();

        if(evalType == EvalType.FILE){
            final List<String> file = readFile(ui.getFilePath());

            request.setN(getNumSize(file.get(0)));
            request.setXs(transformInput(file.get(0)));
            request.setYs(transformInput(file.get(file.size() - 1)));
        } else if(evalType == EvalType.RANDOM) {
            final long n = ui.getNumSize();

            request.setN(n);
            request.setXs(RAND.doubles(n, RAND_LOWER_LIMIT, RAND_UPPER_LIMIT).boxed().collect(Collectors.toList()));
            request.setYs(RAND.doubles(n, RAND_LOWER_LIMIT, RAND_UPPER_LIMIT).boxed().collect(Collectors.toList()));
        } else {
            throw new IllegalArgumentException();
        }

        final String z = ui.getTestValue();

        if(z.equals("q")) System.exit(0);

        request.setZ(Double.parseDouble(z));

        return NewtonsInterpolation.evaluate(request.build());
    }

    private List<String> readFile(final String file) throws IOException {
        return Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
    }

    private List<Double> transformInput(final String s){
       return Arrays.stream(s.replaceAll("\n","").split(" "))
               .map(Double::valueOf)
               .collect(Collectors.toList());
    }

    private long getNumSize(final String s){
        return transformInput(s).size();
    }

    private enum EvalType {
        FILE,
        RANDOM
    }

}
