package cn.duckr.duckr.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import cn.duckr.duckr.Home_HD_Content_Activity;

/**
 * Created by Dizner on 2016/11/11.
 */

public class OnClickGoWeb implements View.OnClickListener {
    private Context context;

    public OnClickGoWeb(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        String url = (String) v.getContentDescription();
        context.startActivity(new Intent(context, Home_HD_Content_Activity.class).putExtra("url",url));
    }
}
