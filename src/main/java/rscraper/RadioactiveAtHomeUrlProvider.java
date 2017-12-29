package rscraper;

public class RadioactiveAtHomeUrlProvider implements UrlProvider {
    private final String urlBase;
    private final int interval;
    private final int startAt;
    private int currentIndex;


    public RadioactiveAtHomeUrlProvider(String urlBase, int startAt, int interval) {
        this.urlBase = urlBase;
        this.interval = interval;
        this.startAt = this.currentIndex = startAt;
    }

    @Override
    public synchronized String getNextUrl() {
        String result = this.urlBase + this.currentIndex;
        this.currentIndex += this.interval;
        return result;
    }

}
