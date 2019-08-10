package top.losttime.notificationstatistics.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;

import com.jeremyliao.liveeventbus.LiveEventBus;

import top.losttime.notificationstatistics.MyApplication;
import top.losttime.notificationstatistics.constant.Constants;
import top.losttime.notificationstatistics.data.db.WechatMsgRepository;
import top.losttime.notificationstatistics.data.entity.NewNotificationEntity;
import top.losttime.notificationstatistics.data.entity.WechatMsg;
import top.losttime.notificationstatistics.manager.WechatInfoManager;

public class MyNotificationListenerService extends NotificationListenerService {

    WechatMsgRepository mRepository = new WechatMsgRepository(MyApplication.getInstance());
    NewNotificationEntity entity = new NewNotificationEntity();

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);

        if (!"com.tencent.mm".equals(sbn.getPackageName())) {
            return;
        }

        Notification notification = sbn.getNotification();
        if (notification == null) {
            return;
        }

        PendingIntent pendingIntent = null;
        String title = "";
        String content = "";
        Bundle extras = notification.extras;
        if (extras != null) {
            title = extras.getString(Notification.EXTRA_TITLE, "");
            content = extras.getString(Notification.EXTRA_TEXT, "");
            if (!TextUtils.isEmpty(content) && content.contains("[微信红包]")) {
                pendingIntent = notification.contentIntent;
                entity.totalRPCount ++;
            }
        }
        try {
            if (pendingIntent != null) {
                pendingIntent.send();
            }
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
        entity.totalMsgCount ++;
        entity.newestSender = title;
        entity.newestContent = content;
        entity.newestReceiveTime = System.currentTimeMillis();
        WechatInfoManager.setCache(entity);

        // insert into db
        WechatMsg wechatMsg = new WechatMsg(entity.newestSender, entity.newestContent,
                entity.newestReceiveTime);
        mRepository.insert(wechatMsg);

        LiveEventBus.get().with(Constants.KEY_NEW_NOTIFICATION).post(entity);

        // 取消通知
        //cancelNotification(sbn.getKey());
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        entity = WechatInfoManager.getCache();
    }

    @Override
    public void onListenerDisconnected() {
        super.onListenerDisconnected();
    }
}
