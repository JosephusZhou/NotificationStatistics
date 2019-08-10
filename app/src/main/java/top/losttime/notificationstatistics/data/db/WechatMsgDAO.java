package top.losttime.notificationstatistics.data.db;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import top.losttime.notificationstatistics.data.entity.WechatMsg;

@Dao
public interface WechatMsgDAO {

    @Insert
    void insert(WechatMsg wechatMsg);

    @Query("SELECT * FROM wechat_msg ORDER BY id DESC")
    LiveData<List<WechatMsg>> getAllMsgs();

    @Query("SELECT * FROM wechat_msg ORDER BY id DESC LIMIT :limit")
    List<WechatMsg> getLastMsgs(int limit);

    @Query("SELECT * FROM wechat_msg WHERE id < :id ORDER BY id DESC LIMIT :limit")
    List<WechatMsg> getMsgs(int id, int limit);

    @Query("SELECT * FROM wechat_msg ORDER BY id DESC")
    DataSource.Factory<Integer, WechatMsg> getPagingMsgs();

}
