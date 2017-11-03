package simple;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposable;

public class AnotherObservable {
    enum Irrelevant { INSTANCE; }

    public static void main(String[] args) {
        AnotherObservable anotherObservable = new AnotherObservable();
        anotherObservable.someFun();
    }
    void someFun(){
        Observable<String> source = Observable.create((ObservableEmitter<String> emitter) -> {
            emitter.onNext("Side-effect 1");

            emitter.onNext("Side-effect 2");

            emitter.onNext("Side-effect 3");
            emitter.onNext("Side-effect 4");
        });

        Disposable d = source.skip(1).take(2).subscribe(System.out::println, Throwable::printStackTrace);
        d.dispose();
        Maybe<Integer> maybe = Maybe.just(1);
        Maybe<Integer> maybe2 = Maybe.empty();
    }

}
