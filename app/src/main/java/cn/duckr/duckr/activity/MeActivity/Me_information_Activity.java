package cn.duckr.duckr.activity.MeActivity;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import cn.duckr.duckr.Me_information_photo_dialoginface;
import cn.duckr.duckr.R;
import cn.duckr.duckr.bean.Login;
import cn.duckr.duckr.view.Login_view;


public class Me_information_Activity extends Activity implements Login_view<Login> {

    private Context context;

    private TextView me_information_name_text,
            me_information_age_text,
            me_information_height_text,
            me_information_comefrom_text,
            me_information_school_text,
            me_information_industry_text,
            me_information_postjob_text,
            me_information_montyly_text,
            me_information_hobby_text,
            me_information_signature_text = null;
    private ImageView me_information_ivphoto;
    private Login.DataBean.UserBean user;
    private DbManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_me_information);
//        Intent intent=getIntent();
//        Bundle extras = intent.getExtras();
        db=x.getDb(new DbManager.DaoConfig());
        try {
            user = db.selector(Login.DataBean.UserBean.class).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        initView();
        initData();

    }

    private void initData() {
        x.image().bind(me_information_ivphoto,user.getAvatarUrl());
        me_information_age_text.setText(user.getBirthday());
        me_information_name_text.setText(user.getNick());
    }

    private void initView() {
        me_information_name_text = (TextView) findViewById(R.id.me_information_name_text);
        me_information_age_text = (TextView) findViewById(R.id.me_information_age_text);
        me_information_height_text = (TextView) findViewById(R.id.me_information_height_text);
        me_information_comefrom_text = (TextView) findViewById(R.id.me_information_comefrom_text);
        me_information_school_text = (TextView) findViewById(R.id.me_information_school_text);
        me_information_industry_text = (TextView) findViewById(R.id.me_information_industry_text);
        me_information_postjob_text = (TextView) findViewById(R.id.me_information_postjob_text);
        me_information_montyly_text = (TextView) findViewById(R.id.me_information_montyly_text);
        me_information_hobby_text = (TextView) findViewById(R.id.me_information_hobby_text);
        me_information_signature_text = (TextView) findViewById(R.id.me_information_signature_text);
        me_information_ivphoto= (ImageView) findViewById(R.id.me_information_ivphoto);
    }

    public void onclick(View view){
        switch (view.getId()){
            case R.id.me_information_name_item:
                Me_information_photo_dialoginface photodialog = new Me_information_photo_dialoginface(context, R.style.AppTheme, new Me_information_photo_dialoginface.me_information_photo_Listener() {
                    @Override
                    public void onClick(View view) {
                        switch (view.getId()){
                            case R.id.me_information_photo_dialog1:
                                //拍摄图片

                                break;
                            case R.id.me_information_photo_dialog2:
                                //选择图片

                                break;
                        }
                    }
                });
                photodialog.setTitle("图片选项");
                photodialog.show();
                break;
            case R.id.me_information_age_item:

                break;
            case R.id.me_information_height_item:

                break;
            case R.id.me_information_city_item:

                break;
            case R.id.me_information_school_item:

                break;
            case R.id.me_information_industry_item:

                break;
            case R.id.me_information_postjob_item:

                break;
            case R.id.me_information_montyly_item:

                break;
            case R.id.me_information_hobby_item:

                break;
            case R.id.me_information_signature_item:

                break;
            case R.id.me_information_address_item:

                break;
        }

    }

    @Override
    public void login(Login obj) {
        Login.DataBean.UserBean user = obj.getData().getUser();

    }

    public void onClick(View view) {
        finish();
    }
}
