package top.losttime.notificationstatistics.manager;

import java.util.concurrent.TimeUnit;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SyncTimeManager implements LifecycleObserver {

    private Disposable disposable;

    private Callback callback;

    private boolean syncFlag;

    private boolean activiteFlag;

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory() {
        syncFlag = false;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        activiteFlag = false;
        cancel();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        activiteFlag = true;
        if (syncFlag) {
            syncTime();
        }
    }

    public SyncTimeManager syncTime() {
        syncFlag = true;
        disposable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(aLong -> System.currentTimeMillis())
                .takeUntil(aLong -> !activiteFlag)
                .subscribe(aLong -> {
                    if (callback != null) callback.onNext(aLong);
                }, throwable -> {
                    if (callback != null) callback.onError();
                });

        return this;
    }

    private void cancel() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void onNext(long time);
        void onError();
    }
}
