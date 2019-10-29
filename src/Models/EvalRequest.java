package Models;

import java.util.List;

public class EvalRequest {
    private final long n;
    private final double z;
    private final List<Double> xs;
    private final List<Double> ys;

    private EvalRequest(final long n, final double z, final List<Double> xs, final List<Double> ys){
        this.n = n;
        this.z = z;
        this.xs = xs;
        this.ys = ys;
    }

    public long getN() {
        return n;
    }

    public double getZ() {
        return z;
    }

    public List<Double> getXs() {
        return xs;
    }

    public List<Double> getYs() {
        return ys;
    }

    public static Builder builder(){
        return Builder.builder();
    }

    public static EvalRequest create(final long n, final double z, final List<Double> xs, final List<Double> ys){
        return new EvalRequest(n, z, xs, ys);
    }

    public static class Builder {
        private long n;
        private double z;
        private List<Double> xs;
        private List<Double> ys;

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
            this.xs = xs;
            return this;
        }

        public Builder setYs(final List<Double> ys){
            this.ys = ys;
            return this;
        }

        public EvalRequest build(){
            return EvalRequest.create(n, z, xs, ys);
        }

        public static Builder builder(){
            return new Builder();
        }

    }
}
