package top.losttime.notificationstatistics.manager;

import top.losttime.notificationstatistics.util.SPUtil;

import top.losttime.notificationstatistics.MyApplication;
import top.losttime.notificationstatistics.constant.Constants;
import top.losttime.notificationstatistics.data.entity.NewNotificationEntity;

public class WechatInfoManager {

    public static NewNotificationEntity getCache() {
        SPUtil spUtil = new SPUtil(MyApplication.getInstance(), Constants.SP_WECHAT_INFO);
        NewNotificationEntity entity = new NewNotificationEntity();
        entity.totalMsgCount = spUtil.getInt(Constants.SP_KEY_MSG_COUNT, 0);
        entity.totalRPCount = spUtil.getInt(Constants.SP_KEY_RP_COUNT, 0);
        entity.newestSender = spUtil.getString(Constants.SP_KEY_NEWEST_SENDER, "");
        entity.newestContent = spUtil.getString(Constants.SP_KEY_NEWEST_CONTENT, "");
        entity.newestReceiveTime = spUtil.getLong(Constants.SP_KEY_NEWEST_RECEIVE_TIME, 0L);
        return entity;
    }

    public static void setCache(NewNotificationEntity entity) {
        SPUtil spUtil = new SPUtil(MyApplication.getInstance(), Constants.SP_WECHAT_INFO);
        spUtil.putInt(Constants.SP_KEY_MSG_COUNT, entity.totalMsgCount);
        spUtil.putInt(Constants.SP_KEY_RP_COUNT, entity.totalRPCount);
        spUtil.putString(Constants.SP_KEY_NEWEST_SENDER, entity.newestSender);
        spUtil.putString(Constants.SP_KEY_NEWEST_CONTENT, entity.newestContent);
        spUtil.putLong(Constants.SP_KEY_NEWEST_RECEIVE_TIME, entity.newestReceiveTime);
    }

}
