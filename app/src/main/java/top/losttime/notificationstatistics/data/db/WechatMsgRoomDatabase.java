package top.losttime.notificationstatistics.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import top.losttime.notificationstatistics.data.entity.WechatMsg;

@Database(entities = {WechatMsg.class}, version = 1)
public abstract class WechatMsgRoomDatabase extends RoomDatabase {

    private static volatile WechatMsgRoomDatabase instance;

    public abstract WechatMsgDAO wechatMsgDAO();

    static WechatMsgRoomDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (WechatMsgRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            WechatMsgRoomDatabase.class, "notification_db")
                            .build();
                }
            }
        }
        return instance;
    }

}
