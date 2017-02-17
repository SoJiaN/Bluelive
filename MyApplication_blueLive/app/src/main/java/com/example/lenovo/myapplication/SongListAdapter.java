package com.example.lenovo.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lenovo on 2017/2/16.
 */

public class SongListAdapter extends BaseAdapter {
    public Context context;
    private List<itemBean> mlist;
    public SongListAdapter(Context context) {
        this.context=context;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.item_status, null);
            holder.title = (TextView) view.findViewById(R.id.title_song);
            holder.number = (TextView) view.findViewById(R.id.number_song);
            holder.tonext = (ImageButton) view.findViewById(R.id.toNextActivity_song);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.title.setText(mlist.get(i).itemtitle);
        holder.number.setText(mlist.get(i).number);
        holder.tonext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                    Intent intent = new Intent(context, SetSongTitle.class);
                    ((Activity)context).startActivityForResult(intent,1);
                }
                if(i==1){
                    Intent intent_1= new Intent(context,SetSongGreenData.class);
                    ((Activity)context).startActivityForResult(intent_1,2);
                }
                if(i==2){
                    Log.e("ssssssssssssss",i+"gray------data");
                }

            }
        });

        return view;
    }




    public void setData(List<itemBean> list){
        this.mlist=list;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public int getCount() {
        if(mlist==null){
            return 0;
        }else {
            return mlist.size();
        }
    }

    public static class ViewHolder {
        public TextView title;
        public TextView number;
        public ImageButton tonext;
    }

}

