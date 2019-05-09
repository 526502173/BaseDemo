package com.anlu.ld.basedemo.glide;

import android.content.Context;
import android.widget.ImageView;

import com.anlu.ld.basedemo.utlis.GlideUtils;
import com.youth.banner.loader.ImageLoader;


/**
 * Created by maoqi on 2019/1/24.
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
        GlideUtils.display(context,(String)o,imageView);
    }
}
