package top.losttime.notificationstatistics.module.main;

import java.util.Objects;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import top.losttime.notificationstatistics.R;
import top.losttime.notificationstatistics.base.BaseToolbarActivity;

public class MainActivity extends BaseToolbarActivity {

    NavController navController;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.main_nav_host_fragment);
        navController = Objects.requireNonNull(navHostFragment).getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

    @Override
    public void onBackPressed() {
        if (onSupportNavigateUp())
            return;

        super.onBackPressed();
    }
}
