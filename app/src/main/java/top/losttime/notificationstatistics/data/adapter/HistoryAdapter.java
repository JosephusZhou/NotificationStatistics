package top.losttime.notificationstatistics.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import top.losttime.notificationstatistics.R;
import top.losttime.notificationstatistics.data.entity.WechatMsg;
import top.losttime.notificationstatistics.util.TimeUtil;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<WechatMsg> list;

    public HistoryAdapter() {
        this.list = new ArrayList<>();
    }

    public void setNewData(List<WechatMsg> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wechat_msg, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        WechatMsg item = list.get(position);
        holder.tvSender.setText(item.getSender());
        holder.tvReceiveTime.setText(TimeUtil.milliseconds2String(item.getReceiveTime(), TimeUtil.DEFAULT_SDF6));
        holder.tvContent.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
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
