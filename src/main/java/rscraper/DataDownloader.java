package rscraper;

import io.reactivex.Observable;

public interface DataDownloader {
    Observable<Measurement> getObserver();
}
