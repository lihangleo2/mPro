package com.leo.myactivityoptions;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import static com.leo.myactivityoptions.R.id.image_pager;

public class ImgBrowsePagerAdapter extends PagerAdapter {

    private ArrayList<String> urls;
    private LayoutInflater inflater;
    private Context context;
    private ImageView view;
    private int index;

    public ImgBrowsePagerAdapter(Context context, ArrayList<String> urls, int index) {
        this.urls = urls;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.index = index;
    }


    @Override
    public int getCount() { // 获得size
        return urls.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);

    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        view = (ImageView) inflater.inflate(R.layout.item_viewpager, null);
        Glide.with(context)
                .load(urls.get(position))
                .asBitmap()
                .dontAnimate()
                .into(view);
        ((ViewPager) container).addView(view);
        if (index == position) {
            ViewCompat.setTransitionName(view, "shareView");
        }
        return view;
    }

    public ImageView getShareView() {
        return view;
    }
}