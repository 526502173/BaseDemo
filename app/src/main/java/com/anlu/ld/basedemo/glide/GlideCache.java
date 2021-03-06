package com.anlu.ld.basedemo.glide;

import android.content.Context;

import com.anlu.ld.basedemo.utlis.StorageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by maoqi on 2019/2/28.
 */
public class GlideCache implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //设置图片的显示格式ARGB_8888(指图片大小为32bit)
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //设置缓存目录(都可以自定义的)
        File storageDirectory = new File(StorageUtils.getImageDirPath(context));
        String downloadDirectoryPath = storageDirectory + "/GlideCache";

        //设置缓存的大小为100M
        int cacheSize = 100 * 1000 * 1000;
        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, cacheSize)
        );
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
