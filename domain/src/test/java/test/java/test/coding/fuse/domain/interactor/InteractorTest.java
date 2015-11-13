package test.java.test.coding.fuse.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Executor;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;
import test.coding.fuse.domain.interactor.Interactor;


public class InteractorTest {

    private Interactor interactor;

    @Mock
    private Executor executionThread;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        interactor = new InteractorTestClass(executionThread,  new TestScheduler());
    }

    @Test
    public void execute() {
        TestSubscriber subscriber = new TestSubscriber();

        interactor.execute(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertValueCount(0);
    }

    @Test
    public void unsubscribe() {
        TestSubscriber testSubscriber = new TestSubscriber();

        interactor.execute(testSubscriber);
        interactor.unsubscribe();

        testSubscriber.isUnsubscribed();
    }

    private class InteractorTestClass extends Interactor {

        protected InteractorTestClass(
                Executor threadExecutor,
                Scheduler uiThread) {
            super(threadExecutor, uiThread);
        }

        @Override
        public Observable buildInteractorObservable() {
            return Observable.empty();
        }

        @Override
        public void execute(Subscriber UseCaseSubscriber) {
            super.execute(UseCaseSubscriber);
        }
    }

}
