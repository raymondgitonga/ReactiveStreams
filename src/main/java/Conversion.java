import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.internal.operators.flowable.FlowableLift;
import io.reactivex.schedulers.Schedulers;

public class Conversion {
    public static void main(String[] args) throws InterruptedException {

        //Observable to Flowable
        Observable.range(1, 1000000)
                .toFlowable(BackpressureStrategy.BUFFER)
                .observeOn(Schedulers.io())
                .subscribe(e -> System.out.println(e + " " + Thread.currentThread().getName()));

        // Flowable to Observable
        Flowable.range(1, 1000000)
                .toObservable()
                .observeOn(Schedulers.io())
                .subscribe(e -> System.out.println(e + " " + Thread.currentThread().getName()));


        Thread.sleep(5000);
    }
}
