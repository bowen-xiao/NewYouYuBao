package com.youlian.youyubao.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youlian.youyubao.R;

/**
 * Created by 肖稳华 on 2017/4/20.
 */

public class ToolImage {


    public static void displayLocalPic(Context context, ImageView imageView, String path) {
        Glide.with(context).load(path).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    public static void displayLocalPic(Context context, ImageView imageView, int resId) {
        displayLocalPic(context,imageView,resId,R.mipmap.ic_launcher);
    }

    public static void displayLocalPic(Context context, ImageView imageView, int resId,int defaultImageView) {
        Glide.with(context).load(resId).placeholder(defaultImageView)
                .error(defaultImageView)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }



    /**
     * 加载圆形图片
     *
     * @param mContext
     * @param path
     * @param imageView
     */
    public static void glideDisplayHeaderImage(Context mContext, ImageView imageView, String path) {
        //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        try {
            Glide.with(mContext)                             //配置上下文
                    .load(path)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)           //设置错误图片
                    .placeholder(R.mipmap.ic_launcher)     //设置占位图片,这里默认使用应用图标
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
