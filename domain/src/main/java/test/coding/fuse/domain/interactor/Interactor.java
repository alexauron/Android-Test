package test.coding.fuse.domain.interactor;

import java.util.concurrent.Executor;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;


public abstract class Interactor {

    private final Executor executionThread;
    private final Scheduler uiThread;

    private Subscription subscription = Subscriptions.empty();

    public Interactor(Executor executionThread, Scheduler uiThread) {
        this.executionThread = executionThread;
        this.uiThread = uiThread;
    }


    public abstract Observable buildInteractorObservable();


    public void execute(Subscriber interactorSubscriber) {
        this.subscription = this.buildInteractorObservable()
                .subscribeOn(Schedulers.from(executionThread))
                .observeOn(uiThread)
                .subscribe(interactorSubscriber);
    }


    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}