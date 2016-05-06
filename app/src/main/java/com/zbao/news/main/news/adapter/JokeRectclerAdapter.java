package com.zbao.news.main.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zbao.news.R;
import com.zbao.news.entity.JokeInf;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangYB on 2016/4/19.
 */
public class JokeRectclerAdapter extends RecyclerView.Adapter<JokeRectclerAdapter.MViewHoder> {

    private List<JokeInf.ResultBean.DataBean> jokeInfs = new ArrayList<JokeInf.ResultBean.DataBean>();

    public JokeRectclerAdapter() {
    }

    public void setData(List<JokeInf.ResultBean.DataBean> jokeInfs) {
        this.jokeInfs = jokeInfs;
        this.notifyDataSetChanged();
    }

    @Override
    public MViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_view, parent, false);
        return new MViewHoder(view);
    }

    @Override
    public void onBindViewHolder(MViewHoder holder, final int position) {
        JokeInf.ResultBean.DataBean dataBean = jokeInfs.get(position);
        holder.updatetime.setText(dataBean.getUpdatetime());
        holder.hashId.setText(dataBean.getHashId());
        holder.content.setText(dataBean.getContent());
        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return jokeInfs.size() == 0 ? 0 : jokeInfs.size();
    }

    public class MViewHoder extends RecyclerView.ViewHolder {

        public MViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Bind(R.id.content)
        TextView content;
        @Bind(R.id.hashId)
        TextView hashId;
        @Bind(R.id.unixtime)
        TextView unixtime;
        @Bind(R.id.update_time)
        TextView updatetime;
        @Bind(R.id.rl_parent)
        RelativeLayout mItem;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * 条目点击回调接口
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
