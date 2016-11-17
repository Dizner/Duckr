package cn.duckr.duckr.activity.MeActivity.Me_setting_Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import cn.duckr.duckr.R;



public class Me_setting_about_web_Activity extends AppCompatActivity {

    private WebView me_setting_webview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_setting_about_web);

        initView();

        initData();

    }

    private void initData() {

        me_setting_webview.loadUrl("http://m.duckr.cn/mobile/");
        me_setting_webview.getSettings().setJavaScriptEnabled(true);

    }

    private void initView() {

        me_setting_webview = (WebView) findViewById(R.id.me_setting_webview);

    }

    public void onclick(View view){
        switch (view.getId()){
            case R.id.me_setting_about_web_back:
                finish();
                break;
        }
    }
    public void onBackPressed() {
        if (me_setting_webview.canGoBack()){
            me_setting_webview.goBack();
        }else{
            super.onBackPressed();
        }
    }

}
