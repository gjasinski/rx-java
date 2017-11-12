package rscraper;

import io.reactivex.Observable;

import java.net.URL;

class RadioactiveAtHomeObservableProvider implements UrlProvider{
    private final static String BASE_URL = "http://radioactiveathome.org/boinc/gettrickledata.php?start=";
    private final static int RANGE = 5_000;
    private final Observable<URL> observable;

    RadioactiveAtHomeObservableProvider(){
        //this.observable = Observable.create
    }


    @Override
    public Observable<URL> getObservable() {
        return null;
    }
}
