package top.losttime.notificationstatistics.module.main;


import android.os.Bundle;

import java.util.Objects;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import top.losttime.notificationstatistics.R;
import top.losttime.notificationstatistics.base.BaseFragment;
import top.losttime.notificationstatistics.data.adapter.HistoryPagingAdapter;
import top.losttime.notificationstatistics.data.db.WechatMsgViewModel;
import top.losttime.notificationstatistics.widget.divider.HorizontalDividerItemDecoration;

public class HistoryFragment extends BaseFragment {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    //private HistoryAdapter adapter;

    private HistoryPagingAdapter pagingAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_history;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                .sizeResId(R.dimen.dp_0_5).colorResId(R.color.c_dcdcdc)
                .margin(0, 0).build());
        recyclerView.setHasFixedSize(true);
        //adapter = new HistoryAdapter();
        pagingAdapter = new HistoryPagingAdapter();
        recyclerView.setAdapter(pagingAdapter);

        WechatMsgViewModel mWechatMsgViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(WechatMsgViewModel.class);
        //mWechatMsgViewModel.getWechatMsgs().observe(this, wechatMsgs -> adapter.setNewData(wechatMsgs));
        mWechatMsgViewModel.getPagingWechatMsgs().observe(this, wechatMsgs -> pagingAdapter.submitList(wechatMsgs));
    }

}
