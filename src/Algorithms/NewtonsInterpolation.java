package Algorithms;

import Models.EvalRequest;
import Models.Evaluation;

import java.util.ArrayList;
import java.util.List;


public class NewtonsInterpolation {

    private NewtonsInterpolation(){}

    public static Evaluation evaluate(final EvalRequest request){
        return evaluate(request.getN(), request.getXs(), request.getYs(), request.getZ());
    }

    public static Evaluation evaluate(final long n, final List<Double> xs, final List<Double> ys, final double z){
        final long start = System.currentTimeMillis();
        final List<Double> cs = coefficients(n, xs, ys);
        final double result = evaluateNewton(n, xs, cs, z);
        final long end = System.currentTimeMillis();

        return Evaluation.create(n, z, xs, ys, cs, result, (end - start));
    }

    private static List<Double> coefficients(final long n, final List<Double> xs, final List<Double> ys){
        final List<Double> cs = new ArrayList<>(ys);

        for(long j = 1; j < n; ++j){
            for(long i = n - 1; i >= j; --i){
                final double y =  cs.get(Math.toIntExact(i)) - cs.get(Math.toIntExact(i - 1));
                final double x = xs.get(Math.toIntExact(i)) - xs.get(Math.toIntExact(i - j));

                final double m = y / x;
                cs.set(Math.toIntExact(i), m);
            }
        }

        return cs;
    }

    private static double evaluateNewton(final long n, final List<Double> xs, final List<Double> cs, final double z){
        double result = cs.get(Math.toIntExact(n - 1));

        for(long i = n - 2; i >= 0; --i){
            result = result * (z - xs.get(Math.toIntExact(i))) + cs.get(Math.toIntExact(i));
        }

        return result;
    }

    public static Evaluation evaluate(final int n, final double[] xs, final double[] ys, final double[] cs, final double z){
        final long start = System.currentTimeMillis();
        coeff(n, xs, ys, cs);
        final double result = evalNewton(n, xs, cs, z);
        final long end = System.currentTimeMillis();

        return Evaluation.create(n, z, xs, ys, cs, result, (end - start));
    }

    public static void coeff(final int n, final double[] xs, final double[] ys, final double[] cs){
        if (n >= 0) System.arraycopy(ys, 0, cs, 0, n);

        for(int j = 1; j < n; ++j){
            for(int i = n - 1; i >= j; --i){
                cs[i] = (cs[i] - cs[i - 1]) / (xs[i] - xs[i - j]);
            }
        }
    }

    public static double evalNewton(final int n, final double[] xs, final double[] cs, final double z){
        double result = cs[n - 1];

        for(int i = n - 2; i >= 0; --i){
            result = result * (z - xs[i]) + cs[i];
        }

        return result;
    }

}