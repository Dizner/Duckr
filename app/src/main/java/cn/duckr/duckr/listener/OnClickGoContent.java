package cn.duckr.duckr.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import cn.duckr.duckr.Home_YaoYue_contentActivity;

/**
 * Created by Dizner on 2016/11/11.
 */

public class OnClickGoContent implements View.OnClickListener {
    private Context context;

    public OnClickGoContent(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        String url = (String) v.getContentDescription();
        context.startActivity(new Intent(context, Home_YaoYue_contentActivity.class).putExtra("url",url));
    }
}
