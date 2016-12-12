package com.dong.starsmind.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by zengwendong on 16/8/12.
 */
public class ImageLoadUtil {

    public static void load(Context context, String url, ImageView imageView, final LoadListener loadListener) {
        Glide.with(context).load(url).crossFade().listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                if (loadListener != null) {
                    loadListener.onFailed();
                }
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (loadListener != null) {
                    loadListener.onSuccess();
                }
                return false;
            }
        }).into(imageView);
    }

    public static void load(Context context, String url, ImageView imageView) {
        load(context, url, imageView, null);
    }

    public static void loadGif(Context context, String url, ImageView imageView, final LoadListener loadListener) {
        Glide.with(context).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().listener(new RequestListener<String, GifDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GifDrawable> target, boolean isFirstResource) {
                if (loadListener != null) {
                    loadListener.onFailed();
                }
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, String model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (loadListener != null) {
                    loadListener.onSuccess();
                }
                return false;
            }
        }).into(imageView);
    }

    public static void loadGif(Context context, String url, ImageView imageView) {
        loadGif(context, url, imageView, null);
    }


    public interface LoadListener {
        void onFailed();

        void onSuccess();
    }

}
