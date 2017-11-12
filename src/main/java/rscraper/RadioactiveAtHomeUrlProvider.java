package rscraper;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import java.net.MalformedURLException;
import java.net.URL;

public class RadioactiveAtHomeUrlProvider implements Runnable, ObservableOnSubscribe {
    private final String urlBase;
    private final int range;
    private final int startAt;
    private final Observable<URL> observable;
    private volatile boolean continueWorking = true;


    public RadioactiveAtHomeUrlProvider(Observable<URL> observable, String urlBase, int range, int startAt) {
        this.urlBase = urlBase;
        this.range = range;
        this.startAt = startAt;
        this.observable = observable;
    }

    @Override
    public void run() {
        try {
            int currMeasuerementIf = startAt;
            while (continueWorking) {
                URL newUrl = new URL(urlBase + currMeasuerementIf);
//                this.observable
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe(ObservableEmitter observableEmitter) throws Exception {

    }
}
