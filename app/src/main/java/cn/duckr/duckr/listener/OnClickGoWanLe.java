package cn.duckr.duckr.listener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.duckr.duckr.PhotoActivity;
import cn.duckr.duckr.bean.WanLe_info;

/**
 * Created by Dizner on 2016/11/11.
 */

public class OnClickGoWanLe implements View.OnClickListener {
    private Context context;
    private WanLe_info data;
    private int tag;

    public OnClickGoWanLe(Context context, WanLe_info data) {
        this.context = context;
        this.data = data;
        this.tag = tag;
    }

    @Override
    public void onClick(View v) {

        Bundle bundle=new Bundle();
        bundle.putSerializable("login",data);
        context.startActivity(new Intent(context, PhotoActivity.class).putExtras(bundle));
    }
}
