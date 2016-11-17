package cn.duckr.duckr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import cn.duckr.duckr.utils.LocaltionUtils;
public class welcomeActivity extends AppCompatActivity {

    private static final int UI_ANIMATION_DELAY = 500;
    private final Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(welcomeActivity.this,MainActivity.class));
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //初始化位置信息
        new LocaltionUtils(this).getLocation();
        mHandler.sendEmptyMessageDelayed(1,UI_ANIMATION_DELAY);
    }

}
