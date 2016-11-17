package cn.duckr.duckr.activity.MeActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import cn.duckr.duckr.R;


public class Me_invitation_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_me_invitation);
    }
}
