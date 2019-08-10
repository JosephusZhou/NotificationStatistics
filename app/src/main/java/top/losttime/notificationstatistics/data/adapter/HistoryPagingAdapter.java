package top.losttime.notificationstatistics.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import top.losttime.notificationstatistics.R;
import top.losttime.notificationstatistics.data.entity.WechatMsg;
import top.losttime.notificationstatistics.util.TimeUtil;

public class HistoryPagingAdapter extends PagedListAdapter<WechatMsg, HistoryPagingAdapter.HistoryViewHolder> {

    public HistoryPagingAdapter() {
        super(new DiffUtil.ItemCallback<WechatMsg>() {

            @Override
            public boolean areItemsTheSame(@NonNull WechatMsg oldItem, @NonNull WechatMsg newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull WechatMsg oldItem, @NonNull WechatMsg newItem) {
                return oldItem == newItem;
            }
        });
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wechat_msg, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        WechatMsg item = getItem(position);
        if (item != null) {
            holder.tvSender.setText(item.getSender());
            holder.tvReceiveTime.setText(TimeUtil.milliseconds2String(item.getReceiveTime(), TimeUtil.DEFAULT_SDF6));
            holder.tvContent.setText(item.getContent());
        }
    }


    class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvSender;
        TextView tvReceiveTime;
        TextView tvContent;

        HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSender = itemView.findViewById(R.id.tv_sender);
            tvReceiveTime = itemView.findViewById(R.id.tv_receive_time);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
