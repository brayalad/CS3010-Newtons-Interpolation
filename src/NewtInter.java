import Algorithms.NewtonsInterpolation;
import Engine.Engine;
import Models.Evaluation;
import UI.TextUserInterface;
import UI.UserInterface;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;


public class NewtInter {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    public static void main(final String[] args) {
        final Scanner input = new Scanner(System.in);
        final UserInterface ui = new TextUserInterface(input);
        final Engine engine = new Engine(ui);

        try {
            if(args != null && args.length > 0) runNewton(args[0]);

            engine.run();
        } catch (Exception e){
            System.err.printf("Error was encountered: %s \n", e.getLocalizedMessage());
        }

    }

    private static void runNewton(final String arg) throws IOException {
        if(arg == null || arg.isEmpty()) return;

        List<String> lines = Collections.emptyList();


        long n;
        if(isLongNum(arg)){
            n = Long.parseLong(arg);
        } else if (isFilePath(arg)){
            System.out.printf("File %s has been read into the program.\n", arg);
            lines = Files.readAllLines(Paths.get(arg), StandardCharsets.UTF_8);
            n = getNumSize(lines.get(0));
        } else {
            return;
        }

        final double[] xs = new double[(int) (n + 1)];
        final double[] ys = new double[(int) (n + 1)];
        final double[] cs = new double[(int) (n + 1)];

        if(isLongNum(arg)){
            if (n >= 0) System.arraycopy(RANDOM.doubles(n).toArray(), 0, xs, 0, (int) n);
            if (n >= 0) System.arraycopy(RANDOM.doubles(n).toArray(), 0, ys, 0, (int) n);
        } else if(isFilePath(arg) && !lines.isEmpty()){
            if (n >= 0) System.arraycopy(transformInput(lines.get(0)), 0, xs, 0, (int) n);
            if (n >= 0) System.arraycopy(transformInput(lines.get(1)), 0, ys, 0, (int) n);
        } else {
            return;
        }

        final String z = getValueFromUser();

        if(z.equals("q")) System.exit(0);

        final Evaluation result = NewtonsInterpolation.evaluate((int) n, xs, ys, cs, Double.parseDouble(z));

        System.out.printf("Newton Interpolation Evaluation Result:\n %s \n", result);
    }

    private static double[] transformInput(final String s){
        return Arrays.stream(s.replaceAll("\n","").split(" "))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    private static String getValueFromUser(){
        System.out.print("Enter value to test against (q to exit): ");
        return SCANNER.nextLine();
    }

    private static long getNumSize(final String s){
        return transformInput(s).length;
    }

    private static boolean isFilePath(final String s){
        try {
            Paths.get(s);
        } catch (InvalidPathException e){
            return false;
        }
        return true;
    }

    private static boolean isLongNum(final String s){
        try {
            Long.parseLong(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
