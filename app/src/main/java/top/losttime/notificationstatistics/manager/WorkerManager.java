package top.losttime.notificationstatistics.manager;

import androidx.work.WorkManager;

import top.losttime.notificationstatistics.MyApplication;

/**
 * @author senfeng.zhou
 * @date 2022/4/21
 * @desc
 */
public class WorkerManager {

    public static void cancelAllJob() {
        WorkManager.getInstance(MyApplication.getInstance()).cancelAllWork();
    }

}
