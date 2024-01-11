package eu.statnett.powersystem.dto;

public class AveragePowerResult {

    private Double averagePower;
    private String time;

    public AveragePowerResult(Double averagePower, String time) {
        this.averagePower = averagePower;
        this.time = time;
    }

    @Override
    public String toString() {
        return "AveragePowerResult{" +
                "averagePower=" + averagePower +
                ", time='" + time + '\'' +
                '}';
    }
}
