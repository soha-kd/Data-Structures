public class NormalPDF implements ContinuousFunction {
    private double mu;
    private double sigma;
    public NormalPDF(double mu, double sigma) {
        this.mu = mu;
        this.sigma = sigma;
    }
    public double eval(double x) {
        return Math.exp(- x * x / 2) / Math.sqrt(2 * Math.PI);  // mu, sigma
    }
}
