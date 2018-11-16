package com.leo.myactivityoptions.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Created by lihang on 2017/9/15.
 */

public class MyGlideModule implements GlideModule {

    int diskSize = 1024 * 1024 * 100;
    int memorySize = (int) (Runtime.getRuntime().maxMemory()) / 8;  // 取1/8最大内存作为最大缓存


    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        //自定义图片质量
//        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);


//        下面这样配置的话,缓存会在 SDCard/Android/data/应用包名/cache/image_manager_disk_cache目录下
        //设置磁盘缓存
//        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, SystemConst.merorySize));

//        builder.setDiskCache(
//                new DiskLruCacheFactory("glide", 150 * 1024 * 1024));
//
//        builder.setMemoryCache(new LruResourceCache(150 * 1024 * 1024));
//        builder.setBitmapPool(new LruBitmapPool(50 * 1024 * 1024));


//        如果需求修改缓存路径的话,需要增加一下参数即可(变为SDCard/Android/data/应用包名/cache/xiayu)
        //设置磁盘缓存大小
//        int size = 100 * 1024 * 1024;
//        String dir = "xiayu";
//        //设置磁盘缓存
//        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,dir, size));


        /**
         * 测试！！
         */
        // 定义缓存大小和位置
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskSize));  //内存中
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "cache", diskSize)); //sd卡中


        // 自定义内存和图片池大小
        builder.setMemoryCache(new LruResourceCache(memorySize));
        builder.setBitmapPool(new LruBitmapPool(memorySize));


    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new ProgressInterceptor());
        OkHttpClient okHttpClient = builder.build();
        glide.register(GlideUrl.class, InputStream.class, new OkHttpGlideUrlLoader.Factory(okHttpClient));
    }
}
