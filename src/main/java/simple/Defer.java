package simple;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.ReplaySubject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Defer {
    public static void main(String[] args) throws IOException, InterruptedException {
        /*ConnectableObservable<Long> cold = Observable.interval(200, TimeUnit.MILLISECONDS).replay();
        Subscription s = cold.connect();

        System.out.println("Subscribe first");
        Subscription s1 = cold.subscribe(i -> System.out.println("First: " + i));
        Thread.sleep(700);
        System.out.println("Subscribe second");
        Subscription s2 = cold.subscribe(i -> System.out.println("Second: " + i));
        Thread.sleep(500);
        System.in.read();*/
    }
}
