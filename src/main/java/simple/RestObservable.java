package simple;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class RestObservable {
    RestClient restClient = new RestClient();
    AnotherRestClient anotherRestClient = new AnotherRestClient();

    public static void main(String[] args) {
        RestObservable restObservable = new RestObservable();
        restObservable.someFun();
    }

    void someFun() {
        //restClient.getFavoriteBooks().stream().map(String::toString).subscribe(System.out.print("A"));
        Observable<List<String>> fractionObservable1 = Observable.fromCallable(() -> restClient.getFavoriteBooks());
        Observable<List<String>> fractionObservable2 = Observable.fromCallable(() -> anotherRestClient.getFavoriteBooks());
        Observable<List<String>> fractionObservable = Observable.merge(fractionObservable1, fractionObservable2);
        fractionObservable.subscribeOn(Schedulers.io()).subscribe(
                t -> System.out.println(t.toString()),
                e -> System.out.println(e.toString()),
                () -> System.out.println("End!")
        );

        int i = 0;
        while (true) {
            System.out.println(i++);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
            }
            fractionObservable.take(3).subscribeOn(Schedulers.io()).subscribe(
                    t -> System.out.println(t.toString()),
                    e -> System.out.println(e.toString()),
                    () -> System.out.println("End!")
            );
        }
    }
}
