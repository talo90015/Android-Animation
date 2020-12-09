package com.talo.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BaseAdapter extends android.widget.BaseAdapter {
    private Context mcontext;
    private int[] mintegers;

    public BaseAdapter(Context context, int[] integers){
        mcontext = context;
        mintegers = integers;
    }

    @Override
    public int getCount() {
        return mintegers.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null){
            imageView = new ImageView(mcontext);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(new GridView.LayoutParams(params));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(10, 10, 10, 10);
        }else
            imageView = (ImageView) convertView;
            imageView.setImageResource(mintegers[position]);

        return imageView;
    }
}
