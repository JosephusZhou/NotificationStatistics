package top.losttime.notificationstatistics.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wechat_msg")
public class WechatMsg {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "sender")
    private String sender;

    @NonNull
    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "receiveTime")
    private long receiveTime;

    public WechatMsg(String sender, String content, long receiveTime) {
        this.sender = sender;
        this.content = content;
        this.receiveTime = receiveTime;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public long getReceiveTime() {
        return receiveTime;
    }
}
