package top.losttime.notificationstatistics.base;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.android.material.appbar.AppBarLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import top.losttime.notificationstatistics.R;

public abstract class BaseToolbarActivity extends BaseActivity {

    @Nullable
    @BindView(R.id.layout_appbar)
    protected AppBarLayout appbar;

    @Nullable
    @BindView(R.id.layout_toolbar)
    protected Toolbar toolbar;

    @Nullable
    protected ActionBar actionBar;

    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
    }

    protected boolean isBack() {
        return false;
    }

    protected void initToolbar() {
        if (toolbar == null)
            return;

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            if (TextUtils.isEmpty(title)) {
                title = getString(R.string.app_name);
            }
            actionBar.setTitle(title);
            if (isBack()) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationIcon(R.mipmap.ic_back);
            }
        }

        if (appbar != null && Build.VERSION.SDK_INT >= 21) {
            appbar.setElevation(10.6f);
        }
    }

    protected void setToolbarTitle(String title) {
        this.title = title;
    }

    protected void setToolbarTitle(int title) {
        this.title = getString(title);
    }

    private void setBackAction(boolean show) {
        if (toolbar == null || actionBar == null)
            return;

        actionBar.setDisplayHomeAsUpEnabled(show);
        if (show)
            toolbar.setNavigationIcon(R.mipmap.ic_back);
    }
}
