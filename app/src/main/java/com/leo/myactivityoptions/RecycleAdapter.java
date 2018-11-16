package com.leo.myactivityoptions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.leo.myactivityoptions.localdemo.bean.LocalBean;
import com.leo.myactivityoptions.utils.UIUtil;

import java.util.ArrayList;


/**
 * Created by liujinhua on 15/9/10.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> dataList;
    Context context;
    View.OnClickListener listener;

    public RecycleAdapter(Context context, View.OnClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public ArrayList<String> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<String> dataList) {
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
        String item = dataList.get(position);
        VH vhHolder = (VH) holder;
        RelativeLayout.LayoutParams reParams = (RelativeLayout.LayoutParams) vhHolder.imageView.getLayoutParams();
        reParams.height = (UIUtil.getWidth(context) - UIUtil.dip2px(context, 30)) / 3;
        Glide.with(context)
                .load(item)
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
