package top.losttime.notificationstatistics.worker;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import top.losttime.notificationstatistics.MyApplication;

/**
 * @author senfeng.zhou
 * @date 3/1/21
 * @desc
 */
public class MorningWorker extends Worker {

    private final static String TAG = "morningWorker";

    public MorningWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        setJob();

        PackageManager pm = getApplicationContext().getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("com.larksuite.suite");
        getApplicationContext().startActivity(intent);

        return Result.success();
    }

    public static void setJob() {
        Calendar currentDate = Calendar.getInstance();
        Calendar dueDate = Calendar.getInstance();

        // 设置在大约 9:26:00 执行
        dueDate.set(Calendar.HOUR_OF_DAY, 9);
        dueDate.set(Calendar.MINUTE, 26);
        dueDate.set(Calendar.SECOND, 0);

        if (dueDate.before(currentDate)) {
            dueDate.add(Calendar.HOUR_OF_DAY, 24);
        }

        // 跳过周六周日
        int weekDay = dueDate.get(Calendar.DAY_OF_WEEK);
        if (weekDay == Calendar.SATURDAY) {
            dueDate.add(Calendar.HOUR_OF_DAY, 48);
        } else if (weekDay == Calendar.SUNDAY) {
            dueDate.add(Calendar.HOUR_OF_DAY, 24);
        }

        long timeDiff = dueDate.getTimeInMillis() - currentDate.getTimeInMillis();
        WorkRequest dailyWorkRequest = new OneTimeWorkRequest.Builder(MorningWorker.class)
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .addTag(TAG)
                .build();
        WorkManager.getInstance(MyApplication.getInstance()).enqueue(dailyWorkRequest);
    }
}
