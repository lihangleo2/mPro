package com.leo.myactivityoptions.localdemo;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leo.myactivityoptions.R;
import com.leo.myactivityoptions.localdemo.bean.LocalBean;
import com.leo.myactivityoptions.utils.UIUtil;

import java.util.ArrayList;


/**
 * Created by liujinhua on 15/9/10.
 */
public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<LocalBean> dataList;
    Context context;
    View.OnClickListener listener;

    public GridAdapter(Context context, View.OnClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public ArrayList<LocalBean> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<LocalBean> dataList) {
        this.dataList = dataList;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    //创建ViewHolder的时候要
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gridview, parent, false);
        holder = new VH(view);

        return holder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        LocalBean item = dataList.get(position);
        VH vhHolder = (VH) holder;
        RelativeLayout.LayoutParams reParams = (RelativeLayout.LayoutParams) vhHolder.imageView.getLayoutParams();
        reParams.height = (UIUtil.getWidth(context) - UIUtil.dip2px(context, 30)) / 3;
        Glide.with(context)
                .load(item.getMipmap())
                .asBitmap()
                .dontAnimate()
                .placeholder(R.mipmap.wutu)
                .error(R.mipmap.wutu)
                .into(vhHolder.imageView);
        vhHolder.imageView.setOnClickListener(listener);
        vhHolder.imageView.setTag(R.id.imageView, position);
    }


    //获取Item的数量，因为添加了Header和Footer，View的数量应该加2
    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class VH extends RecyclerView.ViewHolder {
        ImageView imageView;

        public VH(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }

    }


}
