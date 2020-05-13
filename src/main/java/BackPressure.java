import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;

public class BackPressure {
    public static void main(String[] args) throws InterruptedException {
        Flowable.range(1, 1000000)
                .map(e -> {
                    System.out.println("Produced item is : " + e + " : " + Thread.currentThread().getName());
                    return e;
                })
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    Subscription s;
                    AtomicInteger count = new AtomicInteger(0);
                               @Override
                               public void onSubscribe(Subscription s) {
                                   this.s = s;
                                   System.out.println("Asking 20 minutes");
                                   s.request(20);
                               }

                               @Override
                               public void onNext(Integer integer) {
                                   System.out.println("The subscriber cpnsumed : "+ integer);

                                   if (count.getAndIncrement() % 20 == 0){
                                       System.out.println("Asking for the next 20 items");
                                       s.request(20);
                                   }

                                   try {
                                       Thread.sleep(100);
                                   } catch (InterruptedException e) {
                                       e.printStackTrace();
                                   }

                               }

                               @Override
                               public void onError(Throwable t) {
                                   t.printStackTrace();
                               }

                               @Override
                               public void onComplete() {
                                   System.out.println("Completed");
                               }
                           }

                );

        Thread.sleep(100000000);
    }
}
