package top.losttime.notificationstatistics.data.factory;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import top.losttime.notificationstatistics.data.datasource.HistoryDataSource;
import top.losttime.notificationstatistics.data.db.WechatMsgRepository;
import top.losttime.notificationstatistics.data.entity.WechatMsg;

public class HistoryDataSourceFactory extends DataSource.Factory<Integer, WechatMsg> {

    private WechatMsgRepository wechatMsgRepository;

    public HistoryDataSourceFactory(WechatMsgRepository wechatMsgRepository) {
        this.wechatMsgRepository = wechatMsgRepository;
    }

    @NonNull
    @Override
    public DataSource<Integer, WechatMsg> create() {
        return new HistoryDataSource(wechatMsgRepository);
    }

}
