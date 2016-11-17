package cn.duckr.duckr.activity.MeActivity.Me_setting_Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.duckr.duckr.R;

public class Me_setting_push_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_setting_pushs);
    }

    public void onclick(View v){
        switch (v.getId()){
            case R.id.me_setting_push_back:
                finish();
                break;
        }
    }

}
