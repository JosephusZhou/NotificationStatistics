package top.losttime.notificationstatistics.module.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.Objects;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.OnClick;
import top.losttime.notificationstatistics.R;
import top.losttime.notificationstatistics.base.BaseFragment;
import top.losttime.notificationstatistics.constant.Constants;
import top.losttime.notificationstatistics.data.entity.NewNotificationEntity;
import top.losttime.notificationstatistics.manager.DDWorker;
import top.losttime.notificationstatistics.manager.ToastManager;
import top.losttime.notificationstatistics.manager.WechatInfoManager;
import top.losttime.notificationstatistics.util.NotificationUtils;

public class MainFragment extends BaseFragment {

    @BindView(R.id.tv_cur_time)
    TextView tvCurrentTime;
    @BindView(R.id.tv_auth_status)
    TextView tvAuthStatus;
    @BindView(R.id.btn_auth)
    Button btnAuth;
    @BindView(R.id.tv_service_status)
    TextView tvServiceStatus;
    @BindView(R.id.btn_service)
    Button btnService;
    @BindView(R.id.tv_notification_count)
    TextView tvCount;
    @BindView(R.id.btn_history)
    Button btnHistory;
    @BindView(R.id.btn_dd)
    Button btnDD;


    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        /*SyncTimeManager syncTimeManager = new SyncTimeManager();
        getLifecycle().addObserver(syncTimeManager);
        syncTimeManager.syncTime().setCallback(new SyncTimeManager.Callback() {
            @Override
            public void onNext(long time) {
                tvCurrentTime.setText(String.format("%s%s", getString(R.string.current_time),
                        TimeUtil.milliseconds2String(time, TimeUtil.DEFAULT_SDF6)));
            }

            @Override
            public void onError() {

            }
        });*/

        LiveEventBus.get()
                .with(Constants.KEY_NEW_NOTIFICATION, NewNotificationEntity.class)
                .observe(this, this::setContent);
    }

    @Override
    public void onResume() {
        super.onResume();
        setContent(WechatInfoManager.getCache());
        refreshStatus();
    }

    @OnClick({R.id.btn_auth, R.id.btn_service, R.id.btn_history, R.id.btn_dd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_auth:
                NotificationUtils.openNotificationListenSettings(getContext());
                break;
            case R.id.btn_service:
                NotificationUtils.toggleNotificationListenerService(Objects.requireNonNull(getContext()));
                refreshStatus();
                break;
            case R.id.btn_history:
                Navigation.findNavController(btnHistory).navigate(R.id.action_mainFragment_to_historyFragment);
                break;
            case R.id.btn_dd:
                DDWorker.cancelJob();
                DDWorker.setJob();
                ToastManager.show(getContext(), "成功");
                break;
            default:
                break;
        }
    }

    private void refreshStatus() {
        boolean authStatus = NotificationUtils.isNotificationListenerEnabled(getContext());
        tvAuthStatus.setText(String.format("%s%s", getString(R.string.current_auth_status),
                authStatus ? getString(R.string.success) : getString(R.string.fail)));
        btnAuth.setEnabled(!authStatus);

        boolean serviceStatus = NotificationUtils.isNotificationListnerServiceRunning(getContext());
        tvServiceStatus.setText(String.format("%s%s", getString(R.string.current_service_status),
                serviceStatus ? getString(R.string.running) : getString(R.string.no_running)));
        btnService.setEnabled(!serviceStatus);
    }

    private void setContent(NewNotificationEntity entity) {
        tvCount.setText(String.format("%s%s\n%s%s\n%s\n%s\n%s",
                getString(R.string.total_wechat_msg), entity.totalMsgCount,
                getString(R.string.total_wechat_rp), entity.totalRPCount,
                getString(R.string.newest_wechat_msg),
                entity.newestSender,
                entity.newestContent));
    }
}
