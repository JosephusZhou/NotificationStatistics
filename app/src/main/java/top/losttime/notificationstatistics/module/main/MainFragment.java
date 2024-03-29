package top.losttime.notificationstatistics.module.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import top.losttime.notificationstatistics.R;
import top.losttime.notificationstatistics.base.BaseFragment;
import top.losttime.notificationstatistics.constant.Constants;
import top.losttime.notificationstatistics.data.entity.NewNotificationEntity;
import top.losttime.notificationstatistics.manager.WorkerManager;
import top.losttime.notificationstatistics.worker.AfternoonWorker;
import top.losttime.notificationstatistics.worker.MorningWorker;
import top.losttime.notificationstatistics.manager.ToastManager;
import top.losttime.notificationstatistics.manager.WechatInfoManager;
import top.losttime.notificationstatistics.util.NotificationUtils;

@SuppressLint("NonConstantResourceId")
public class MainFragment extends BaseFragment {

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


    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
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

    @OnClick({R.id.btn_auth, R.id.btn_service, R.id.btn_history, R.id.btn_cancel, R.id.btn_start_morning, R.id.btn_start_afternoon})
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
            case R.id.btn_cancel:
                WorkerManager.cancelAllJob();
                ToastManager.show(getContext(), "成功");
                break;
            case R.id.btn_start_morning:
                MorningWorker.setJob();
                ToastManager.show(getContext(), "成功");
                break;
            case R.id.btn_start_afternoon:
                AfternoonWorker.setJob();
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
