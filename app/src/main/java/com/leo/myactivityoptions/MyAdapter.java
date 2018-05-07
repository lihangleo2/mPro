package com.leo.myactivityoptions;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;

import static android.R.attr.resource;

/**
 * Created by Administrator on 2018/4/17.
 */

public class MyAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> urls;
    private View.OnClickListener listener;

    public MyAdapter(Context context, View.OnClickListener listener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }


    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return urls == null ? 0 : urls.size();
    }

    @Override
    public Object getItem(int i) {
        return urls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gridview, null);
            viewHolder = new ViewHolder();
            viewHolder.initView(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        String myUrl = urls.get(position);
//glide占位图不会变形的加载
        final ViewHolder finalViewHolder = viewHolder;
        Glide.with(context)
                .load(myUrl)
                .asBitmap()
                .dontAnimate()
                .placeholder(R.mipmap.wutu)
                .error(R.mipmap.wutu)
                .into(viewHolder.imageView);
        viewHolder.imageView.setTag(R.id.imageView,position);
        viewHolder.imageView.setOnClickListener(listener);


        return convertView;
    }


    public class ViewHolder {
        ImageView imageView;

        public void initView(View v) {
            imageView = v.findViewById(R.id.imageView);
        }
    }
}
