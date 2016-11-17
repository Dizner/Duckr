package cn.duckr.duckr.activity.MeActivity;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import cn.duckr.duckr.DrukApp;
import cn.duckr.duckr.R;
import cn.duckr.duckr.activity.MeActivity.Me_setting_Activitys.Me_setting_about_Activity;
import cn.duckr.duckr.activity.MeActivity.Me_setting_Activitys.Me_setting_push_Activity;
import cn.duckr.duckr.utils.DIYUtils;


public class Me_setting_Activity extends AppCompatActivity {
    private TextView me_setting_cachesize;
    private CheckBox me_setting_WIFIbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_setting);
        me_setting_cachesize = (TextView) findViewById(R.id.me_setting_cachesize);
        me_setting_WIFIbutton = (CheckBox) findViewById(R.id.me_setting_WIFIbutton);
        try {
            me_setting_cachesize.setText(DIYUtils.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }


        me_setting_WIFIbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DrukApp.setIsWif(true);
                    if (DIYUtils.getNetworkType(Me_setting_Activity.this) == ConnectivityManager.TYPE_MOBILE) {
                        Toast.makeText(Me_setting_Activity.this, "当前处于移动网络，请注意流量消耗", Toast.LENGTH_LONG).show();
                    }

                } else {
                    DrukApp.setIsWif(false);
                    Toast.makeText(Me_setting_Activity.this, "已关闭自动播放", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void onclick(View v) {

        switch (v.getId()) {
            case R.id.me_setting_push:

                startActivity(new Intent(Me_setting_Activity.this, Me_setting_push_Activity.class));

                break;
            case R.id.me_setting_playmedia:

//                break;
//            case R.id.me_setting_WIFIbutton:
//
//                break;
            case R.id.me_setting_cleancache:
                boolean isSuccess = DIYUtils.clearAllCache(this);
                if (isSuccess) {
                    Toast.makeText(this, "清理成功", Toast.LENGTH_SHORT).show();
                    me_setting_cachesize.setText("0MB");
                } else {
                    Toast.makeText(this, "我也不知道为啥清理失败了呢", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.me_setting_update:

                break;
            case R.id.me_setting_grade:

                break;
            case R.id.me_setting_about:

                startActivity(new Intent(Me_setting_Activity.this, Me_setting_about_Activity.class));
                break;
            case R.id.me_setting_Feedback:

                break;
            case R.id.me_setting_logoff:

                break;
            case R.id.me_setting_back:
                finish();
                break;


        }

    }
}
