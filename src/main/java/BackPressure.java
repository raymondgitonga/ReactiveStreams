import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class BackPressure {
    public static void main(String[] args) throws InterruptedException {
        Flowable.range(1, 1000000)
                .map(e -> {
                    System.out.println("Produced item is : " + e + " : " + Thread.currentThread().getName());
                    return e;
                })
                .observeOn(Schedulers.io())
                .subscribe(e -> {
                    Thread.sleep(100);
                    System.out.println("Consumed item is: " + e + " : " + Thread.currentThread().getName());
                });

        Thread.sleep(100000000);
    }
}
