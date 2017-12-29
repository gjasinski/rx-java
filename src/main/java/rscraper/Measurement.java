package rscraper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

class Measurement {
    private final LocalDateTime time;
    private final int counts;
    private final double durationOfMeasurement;
    private final Detector detector;

    Measurement(Detector detector, LocalDateTime time, int counts, double durationOfMeasurement) {
        this.detector = detector;
        this.time = time;
        this.counts = counts;
        this.durationOfMeasurement = durationOfMeasurement;
    }

    public Detector getDetector() {
        return detector;
    }

    public String getRecord(){
        return time + "," + Double.toString(this.counts / this.durationOfMeasurement / 171.2328);
    }

    public long getEpoch(){
        return this.time.toInstant(ZoneOffset.ofHours(1)).getEpochSecond();
    }


    @Override
    public String toString() {
        return "Measurement{" +
                "time=" + time +
                ", counts=" + counts +
                ", durationOfMeasurement=" + durationOfMeasurement +
                ", detector=" + detector +
                '}';
    }
}
