package com.pierce.mvpgank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pierce.mvpgank.R;
import com.pierce.mvpgank.model.entity.Ganhuo;
import com.pierce.mvpgank.model.entity.Gank;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author: pierce
 * Date: 2015/9/6
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHoler> {

    List<Ganhuo> data;
    Context context;
    private OnClickViewListener listener;

    public MainRecyclerViewAdapter(Context context,List data){
        this.context=context;
        this.data=data;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        return new ViewHoler(v);
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        holder.position=position;
        Ganhuo ganhuo=data.get(position);
        holder.tv.setText(ganhuo.getDesc());
        Glide.with(context)
                .load(ganhuo.getUrl())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.img)
        ImageView img;
        @Bind(R.id.tv)
        TextView tv;
        @Bind(R.id.layout_tv)
        LinearLayout layout_tv;

        int position;
        View card;
        public ViewHoler(View view){
            super(view);
            ButterKnife.bind(this, view);
            card=view;
            img.setOnClickListener(this);
            tv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,img,card,position);
        }
    }

    public interface OnClickViewListener{
        void onClick(View v,ImageView imageView,View card,int position);
    }
    public void setOnClickViewListener(OnClickViewListener listener){
        this.listener=listener;
    }
}
