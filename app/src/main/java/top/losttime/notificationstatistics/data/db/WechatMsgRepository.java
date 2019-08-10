package top.losttime.notificationstatistics.data.db;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import top.losttime.notificationstatistics.data.entity.WechatMsg;

public class WechatMsgRepository {

    private WechatMsgDAO wechatMsgDAO;

    private LiveData<List<WechatMsg>> wechatMsgs;

    private DataSource.Factory<Integer, WechatMsg> wechatMsgsDSF;

    public WechatMsgRepository(Application application) {
        WechatMsgRoomDatabase db = WechatMsgRoomDatabase.getInstance(application);
        wechatMsgDAO = db.wechatMsgDAO();
        wechatMsgs = wechatMsgDAO.getAllMsgs();
        wechatMsgsDSF = wechatMsgDAO.getPagingMsgs();
    }

    LiveData<List<WechatMsg>> getWechatMsgs() {
        return wechatMsgs;
    }

    public DataSource.Factory<Integer, WechatMsg> getWechatMsgsDSF() {
        return wechatMsgsDSF;
    }

    public void insert(WechatMsg wechatMsg) {
        new InsertAsyncTask(wechatMsgDAO).execute(wechatMsg);
    }

    private static class InsertAsyncTask extends AsyncTask<WechatMsg, Void, Void> {

        private WechatMsgDAO mAsyncTaskDao;

        InsertAsyncTask(WechatMsgDAO wechatMsgDAO) {
            mAsyncTaskDao = wechatMsgDAO;
        }

        @Override
        protected Void doInBackground(WechatMsg... wechatMsgs) {
            mAsyncTaskDao.insert(wechatMsgs[0]);
            return null;
        }
    }

    public List<WechatMsg> getLastWechatMsgs(int size) {
        return wechatMsgDAO.getLastMsgs(size);
    }

    public List<WechatMsg> getMoreWechatMsgs(int id, int size) {
        return wechatMsgDAO.getMsgs(id, size);
    }
}
