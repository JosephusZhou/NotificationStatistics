package top.losttime.notificationstatistics.base;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import top.losttime.notificationstatistics.R;

public abstract class BaseToolbarFragment extends BaseFragment {

    @Nullable
    @BindView(R.id.layout_appbar)
    protected AppBarLayout appbar;

    @Nullable
    @BindView(R.id.layout_toolbar)
    protected Toolbar toolbar;

    @Nullable
    @BindView(R.id.iv_back)
    protected ImageView ivBack;

    @Nullable
    @BindView(R.id.tv_title)
    protected TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initToolbar();
        return view;
    }

    protected boolean isBack() {
        return false;
    }

    protected void onBackPressed() {
    }

    private void initToolbar() {
        if (toolbar == null)
            return;

        if (isBack()) {
            if (ivBack != null) {
                ivBack.setVisibility(View.VISIBLE);
                ivBack.setOnClickListener(v -> onBackPressed());
            }
        }

        if (appbar != null && Build.VERSION.SDK_INT >= 21) {
            appbar.setElevation(10.6f);
        }
    }

    protected void setToolbarTitle(String title) {
        if (tvTitle != null)
            tvTitle.setText(title);
    }

    protected void setToolbarTitle(int title) {
        if (tvTitle != null)
            tvTitle.setText(title);
    }
}
