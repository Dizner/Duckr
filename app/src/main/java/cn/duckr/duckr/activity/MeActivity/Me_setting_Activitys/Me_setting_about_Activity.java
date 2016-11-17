package cn.duckr.duckr.activity.MeActivity.Me_setting_Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.duckr.duckr.R;

public class Me_setting_about_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_setting_about);
    }


    public void onclick(View v){

        switch (v.getId()){

            case R.id.me_setting_about_back:
                finish();
                break;

            case R.id.www_duckr_cn:
                startActivity(new Intent(Me_setting_about_Activity.this,Me_setting_about_web_Activity.class));
                break;

        }

    }

}
