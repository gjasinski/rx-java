package rscraper;

import io.reactivex.Observable;

import java.net.URL;

interface UrlProvider {
    Observable<URL> getObservable();
}
