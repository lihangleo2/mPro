package com.leo.myactivityoptions.likewx;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.leo.myactivityoptions.R;
import com.leo.myactivityoptions.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import me.panpf.sketch.Sketch;
import me.panpf.sketch.SketchImageView;
import me.panpf.sketch.request.DisplayOptions;

/**
 * Created by leo
 * on 2019/5/13.
 */

public class ReAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> remindList;
    private LayoutInflater inflater;
    private View.OnClickListener listener;


    public ReAdapter(Context context, View.OnClickListener listener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    public void setRemindList(ArrayList<String> remindList) {
        this.remindList = remindList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;
        View v;

        v = inflater.inflate(R.layout.item_grid, parent, false);
        holder = new ViewHolder(v);

        return holder;

    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        ViewHolder holder1 = (ViewHolder) holder;
        String itemUrl = remindList.get(position);
        FrameLayout.LayoutParams cardreParams = (FrameLayout.LayoutParams) holder1.srcImageView.getLayoutParams();
        cardreParams.height = (UIUtil.getWidth(context) - UIUtil.dip2px(context,16)) / 3;
        DisplayOptions displayOptions = new DisplayOptions();
        displayOptions.setLoadingImage(R.mipmap.ic_launcher_leo);
        displayOptions.setErrorImage(R.mipmap.image_error);
        Sketch.with(context).display(itemUrl, holder1.srcImageView)
                .options(displayOptions)
                .commit();
        holder1.srcImageView.setOnClickListener(listener);
        holder1.srcImageView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return remindList == null ? 0 : remindList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        SketchImageView srcImageView;


        public ViewHolder(View itemView) {
            super(itemView);
            srcImageView = itemView.findViewById(R.id.srcImageView);
        }
    }


}