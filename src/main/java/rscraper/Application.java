package rscraper;

import io.reactivex.Observable;

import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application{
    private static final String BASE_URL = "http://37.59.210.4/boinc/gettrickledata.php?start=";
    private static final int START_AT = 299_008_856;
    private static final int INTERVAL = 5_000;
    public static void main(String[] args) throws InterruptedException {
        UrlProvider urlProvider = new RadioactiveAtHomeUrlProvider(BASE_URL, START_AT, INTERVAL);
        ExecutorService executors = Executors.newFixedThreadPool(4);
        Observable<Measurement> observableAllThreads = Observable.empty();
        for(int i = 0; i < 4; i++){
            DataDownloaderImpl dataDownloader = new DataDownloaderImpl(urlProvider);
            Observable<Measurement> observable = dataDownloader.getObserver();
            observableAllThreads = observableAllThreads.mergeWith(observable);
            executors.execute(dataDownloader);
        }
        observableAllThreads.groupBy(Measurement::getDetector).subscribe(group -> {
            PrintWriter writer = new PrintWriter("/home/grzegorz/data/detector-" + group.getKey().getId(), "UTF-8");
            group.subscribe(data -> {writer.println(data.getRecord());
                System.out.println(data.getRecord());});
        });
        executors.shutdown();
    }
}
