package rscraper;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.message.FormattedMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class DataDownloaderImpl implements DataDownloader, Runnable {
    private final Logger LOGGER = Logger.getLogger(DataDownloaderImpl.class);
    private final UrlProvider urlProvider;
    private final PublishSubject<Measurement> publisherSubject;

    public DataDownloaderImpl(UrlProvider urlProvider) {
        this.urlProvider = urlProvider;
        publisherSubject = PublishSubject.create();
    }

    @Override
    public Observable<Measurement> getObserver() {
        return publisherSubject;
    }

    @Override
    public void run() {
        try {
            boolean continueScraping = true;
            while(continueScraping) {
                continueScraping = false;
                URL url = new URL(this.urlProvider.getNextUrl());
                try (InputStream inputStream = url.openStream()) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                    String line = br.readLine();
                    if(!line.equals("NO DATA")){
                        continueScraping = true;
                    }
                    while (line != null) {
                        convertLineToMeasurement(line);
                        line = br.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.publisherSubject.onComplete();
    }

    private void convertLineToMeasurement(String line) {
        try {
            String[] splittedLine = line.split(",");
            LocalDateTime localDateTime = getLocalDateTime(splittedLine[3]);
            int counts = Integer.valueOf(splittedLine[2]);
            double durationOfMeasurement = Double.valueOf(splittedLine[6]);
            Detector detector = new Detector(Integer.valueOf(splittedLine[1]));
            Measurement measurement = new Measurement(detector, localDateTime, counts, durationOfMeasurement);
            publisherSubject.onNext(measurement);
        } catch (Exception ex) {
            //LOGGER.error(new FormattedMessage("Marlformed line {} {}", line, ex.toString()));
            System.out.println(new FormattedMessage("Marlformed line {} {}", line, ex.toString()));
        }
    }

    private LocalDateTime getLocalDateTime(String s) {
        String[] dateTime = s.split(" ");
        LocalDate localDate = convertToLocalDate(dateTime[0]);
        LocalTime localTime = convertToLocalTime(dateTime[1]);
        return LocalDateTime.of(localDate, localTime);
    }

    private LocalDate convertToLocalDate(String date) {
        String[] dateSplitted = date.split("-");
        int year = Integer.valueOf(dateSplitted[0]);
        int month = Integer.valueOf(dateSplitted[1]);
        int day = Integer.valueOf(dateSplitted[2]);
        return LocalDate.of(year, month, day);
    }

    private LocalTime convertToLocalTime(String time) {
        String[] timeSplitted = time.split(":");
        int hour = Integer.valueOf(timeSplitted[0]);
        int minute = Integer.valueOf(timeSplitted[1]);
        int second = Integer.valueOf(timeSplitted[2]);
        return LocalTime.of(hour, minute, second);
    }
}
