package top.losttime.notificationstatistics;

import android.app.Application;

import com.jeremyliao.liveeventbus.LiveEventBus;

import top.losttime.notificationstatistics.util.LogUtil;

public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        new LogUtil.Builder(this)
                .setLogSwitch(BuildConfig.DEBUG)
                .setLog2FileSwitch(false)
                .setBorderSwitch(false)
                .setLogFilter(LogUtil.V);

        LiveEventBus.get()
                .config()
                //.supportBroadcast(this) // 跨进程调用
                .lifecycleObserverAlwaysActive(false);
    }

    public static MyApplication getInstance() {
        return instance;
    }

}
