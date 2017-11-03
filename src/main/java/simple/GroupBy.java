package simple;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class GroupBy {
    public static void main(String[] args) {
        Observable<GroupedObservable<Boolean, Integer>> groupedObservable = Observable.just(0, 1, 2, 3, 4, 5, 6, 7, 8)
                .groupBy(x -> x % 2 == 0);

        groupedObservable.subscribe(group -> {
            if (group.getKey()) {
                processEven(group);
            } else {
                processsOdd(group);
            }
        });
    }

    private static void processEven(GroupedObservable<Boolean, Integer> group) {
        group.subscribe(s-> System.out.println(s.toString()));
    }

    private static void processsOdd(GroupedObservable<Boolean, Integer> group){

    }
}
