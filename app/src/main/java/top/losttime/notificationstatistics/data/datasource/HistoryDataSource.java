package top.losttime.notificationstatistics.data.datasource;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;
import top.losttime.notificationstatistics.data.db.WechatMsgRepository;
import top.losttime.notificationstatistics.data.entity.WechatMsg;

public class HistoryDataSource extends ItemKeyedDataSource<Integer, WechatMsg> {

    private WechatMsgRepository wechatMsgRepository;

    public HistoryDataSource(WechatMsgRepository wechatMsgRepository) {
        this.wechatMsgRepository = wechatMsgRepository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<WechatMsg> callback) {
        List<WechatMsg> list = wechatMsgRepository.getLastWechatMsgs(params.requestedLoadSize);
        if (list != null) {
            callback.onResult(list);
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<WechatMsg> callback) {
        List<WechatMsg> list = wechatMsgRepository.getMoreWechatMsgs(params.key, params.requestedLoadSize);
        if (list != null) {
            callback.onResult(list);
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<WechatMsg> callback) {
        //
    }

    @NonNull
    @Override
    public Integer getKey(@NonNull WechatMsg item) {
        return item.id;
    }
}
