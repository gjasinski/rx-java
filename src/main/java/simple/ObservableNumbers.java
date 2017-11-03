package simple;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import java.util.LinkedList;
import java.util.List;

public class ObservableNumbers {
    List<Fraction> list = new LinkedList<Fraction>();
    ObservableNumbers(){
        list.add(new Fraction(1,2));
        list.add(new Fraction(1,3));
        list.add(new Fraction(1,4));
        list.add(new Fraction(1,5));
        list.add(new Fraction(1,7));
        //list.add(null);

    }
    public static void main(String[] args) {
        ObservableNumbers observableNumbers = new ObservableNumbers();
        observableNumbers.someFun();
    }
    void someFun() {
        Observable<Fraction> fractionObservable = Observable.create(emitter ->{
                try {
                    List<Fraction> fractions = this.list;
                    for (Fraction fraction : fractions) {
                        emitter.onNext(fraction);
                    }
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        );

        Disposable disposable = fractionObservable.subscribe(t -> System.out.println(t.toString()), Throwable::printStackTrace);

        //disposable.dispose();

        DisposableObserver<Fraction> disposableObserver = fractionObservable.subscribeWith(new  DisposableObserver<Fraction>() {

            @Override
            public void onNext(Fraction f) {
                System.out.println("#" + f.toString());
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                System.out.println("end");
            }
        });
        list.add(new Fraction(1,50));
        list.add(new Fraction(1,70));
        Observable<String> o = Observable.just("one object");
        o.subscribe(t-> System.out.println(t));
    }
}
