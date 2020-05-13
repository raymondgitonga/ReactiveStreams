import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class FlowableCreation {
    public static void main(String[] args) throws InterruptedException {
        Flowable.create(emmitter -> {
            for (int i = 0; i<= 5000; i++){
                if (emmitter.isCancelled())
                    return;
                emmitter.onNext(i);
            }
            emmitter.onComplete();
        }, BackpressureStrategy.BUFFER)
                .observeOn(Schedulers.io())
                .subscribe(System.out::println);

        Thread.sleep(2000);
    }
}
