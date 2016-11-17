package cn.duckr.duckr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class Home_HD_Content_Activity extends AppCompatActivity {
    private String url;
    @ViewInject(R.id.home_content_web)
    private WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__hd__content_);
        x.view().inject(this);
        Intent intent=getIntent();
        url = intent.getStringExtra("url");
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
//        LogUtils.out("web",url);
        WebSettings settings = web.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        web.loadUrl(url);
    }

    public void black(View view) {
        finish();
    }

    public void onClick(View view) {
        startActivity(new Intent(Home_HD_Content_Activity.this,ChoosePayActivity.class));
    }
}
