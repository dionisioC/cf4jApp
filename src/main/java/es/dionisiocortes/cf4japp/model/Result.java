package es.dionisiocortes.cf4japp.model;

public class Result {
    double rmseScore;
    long elapsedTime;

    public Result(double rmseScore, long elapsedTime) {
        this.rmseScore = rmseScore;
        this.elapsedTime = elapsedTime;
    }

    public double getRmseScore() {
        return rmseScore;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    @Override
    public String toString() {
        return "Result{" +
                "rmseScore=" + rmseScore +
                ", elapsedTime=" + elapsedTime +
                '}';
    }
}
