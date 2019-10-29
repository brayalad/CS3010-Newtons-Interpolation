package Models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Evaluation {
    private final long n;
    private final double z;
    private final List<Double> xs;
    private final List<Double> ys;
    private final List<Double> cs;
    private final double result;
    private final long runtime;

    private Evaluation(
            final long n,
            final double z,
            final List<Double> xs,
            final List<Double> ys,
            final List<Double> cs,
            final double result,
            final long runtime
    ){
        this.n = n;
        this.z = z;
        this.xs = xs;
        this.ys = ys;
        this.cs = cs;
        this.result = result;
        this.runtime = runtime;
    }

    private Evaluation(
            final long n,
            final double z,
            final double[] xs,
            final double[] ys,
            final double[]cs,
            final double result,
            final long runtime
    ){
        this.n = n;
        this.z = z;
        this.xs = Arrays.stream(xs).boxed().collect(Collectors.toList());
        this.ys = Arrays.stream(ys).boxed().collect(Collectors.toList());
        this.cs = Arrays.stream(cs).boxed().collect(Collectors.toList());
        this.result = result;
        this.runtime = runtime;
    }

    public long getN(){ return n; }

    public double getZ(){ return z; }

    public List<Double> getXs(){ return xs; }

    public List<Double> getYs(){ return ys; }

    public List<Double> getCs(){ return cs; }

    public double getResult(){ return result; }

    public long getRuntime(){ return runtime; }

    public static Builder builder(){ return new Builder(); }

    public static Evaluation create(
            final long n,
            final double z,
            final List<Double> xs,
            final List<Double> ys,
            final List<Double> cs,
            final double result,
            final long runtime
    ){
        return new Evaluation(n, z, xs, ys, cs, result, runtime);
    }

    public static Evaluation create(
            final long n,
            final double z,
            final double[] xs,
            final double[] ys,
            final double[] cs,
            final double result,
            final long runtime
    ){
        return new Evaluation(n, z, xs, ys, cs, result, runtime);
    }

    @Override
    public String toString(){
        return "Number of points: " + n + "\n" +
                "Number tested with: " + z + "\n" +
                "Points on x-axis: " + xs.toString() + "\n" +
                "Points on y-axis: " + ys.toString() + "\n" +
                "Coefficients: " + cs.toString() + "\n" +
                "Result: " + result + "\n" +
                "Evaluation runtime: " + runtime + " millisecond(s)\n";
    }


    public static class Builder{
        private long n;
        private double z;
        private List<Double> xs;
        private List<Double> ys;
        private List<Double> cs;
        private double result;
        private long runtime;

        private Builder(){}

        public Builder setN(final long n){
            this.n = n;
            return this;
        }

        public Builder setZ(final double z){
            this.z = z;
            return this;
        }

        public Builder setXs(final List<Double> xs){
            this.xs = List.copyOf(xs);
            return this;
        }

        public Builder setYs(final List<Double> ys){
            this.ys = List.copyOf(ys);
            return this;
        }

        public Builder setCs(final List<Double> cs){
            this.cs = List.copyOf(cs);
            return this;
        }

        public Builder setResult(final double result){
            this.result = result;
            return this;
        }

        public Builder setRuntime(final long runtime){
            this.runtime = runtime;
            return this;
        }

        public Evaluation build(){
            return new Evaluation(n, z, xs, ys, cs, result, runtime);
        }

    }

}
