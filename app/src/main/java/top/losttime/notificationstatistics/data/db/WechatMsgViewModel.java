package top.losttime.notificationstatistics.data.db;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import top.losttime.notificationstatistics.data.entity.WechatMsg;
import top.losttime.notificationstatistics.data.factory.HistoryDataSourceFactory;

public class WechatMsgViewModel extends AndroidViewModel {

    private LiveData<List<WechatMsg>> mWechatMsgs;

    private LiveData<PagedList<WechatMsg>> mPagingWechatMsgs;

    public WechatMsgViewModel(@NonNull Application application) {
        super(application);

        WechatMsgRepository mRepository = new WechatMsgRepository(application);

        mWechatMsgs = mRepository.getWechatMsgs();

        HistoryDataSourceFactory factory = new HistoryDataSourceFactory(mRepository);

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .build();

        mPagingWechatMsgs = new LivePagedListBuilder<>(mRepository.getWechatMsgsDSF(), config).build();
    }

    public LiveData<List<WechatMsg>> getWechatMsgs() {
        return mWechatMsgs;
    }

    public LiveData<PagedList<WechatMsg>> getPagingWechatMsgs() {
        return mPagingWechatMsgs;
    }
}
