package cn.duckr.duckr.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import cn.duckr.duckr.R;

/**
 * Created by Dizner on 2016/11/15.
 */

public class TakePhotosDialog extends Dialog {




    public TakePhotosDialog(Context context) {
        super(context);
        this.show();
    }

    public TakePhotosDialog(Context context, int theme) {
        super(context, theme);
        this.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_hd_sousuo_diag);
    }


}