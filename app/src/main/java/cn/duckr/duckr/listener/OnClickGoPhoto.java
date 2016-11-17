package cn.duckr.duckr.listener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import cn.duckr.duckr.PhotoActivity;

/**
 * Created by Dizner on 2016/11/11.
 */

public class OnClickGoPhoto implements View.OnClickListener {
    private Context context;
    private ArrayList<String> list;
    private int tag;
    private boolean type=false;
    public OnClickGoPhoto(Context context,ArrayList<String> list) {
        this.context = context;
        this.list=list;
    }
    public OnClickGoPhoto(Context context,ArrayList<String> list,int tag) {
        this.context = context;
        this.list=list;
        this.tag=tag;
    }
    public OnClickGoPhoto(Context context,ArrayList<String> list,boolean type) {
        this.context = context;
        this.list=list;
        this.type=type;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle=new Bundle();
        bundle.putStringArrayList("url",list);
        context.startActivity(new Intent(context, PhotoActivity.class).putExtras(bundle).putExtra("tag",tag).putExtra("type",type));
    }
}
