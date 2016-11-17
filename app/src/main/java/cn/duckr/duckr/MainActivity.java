package cn.duckr.duckr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.duckr.duckr.Fragment.CharFragment;
import cn.duckr.duckr.Fragment.HomeFragment;
import cn.duckr.duckr.Fragment.LocalFragment;
import cn.duckr.duckr.Fragment.MeFragment;
import cn.duckr.duckr.Fragment.ShareFragment;
import cn.duckr.duckr.activity.MeActivity.Me_corpusde_Activity;
import cn.duckr.duckr.activity.MeActivity.Me_information_Activity;
import cn.duckr.duckr.activity.MeActivity.Me_invitation_Activity;
import cn.duckr.duckr.activity.MeActivity.Me_myEvent_Activity;
import cn.duckr.duckr.activity.MeActivity.Me_notes_Activity;
import cn.duckr.duckr.activity.MeActivity.Me_setting_Activity;
import cn.duckr.duckr.bean.Login;
import cn.duckr.duckr.persenter.Base_persenter;
import cn.duckr.duckr.persenter.Login_persenterimpl;
import cn.duckr.duckr.view.Login_view;
public class MainActivity extends AppCompatActivity implements Login_view{
    private List<Fragment> fragList=null;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private int currentfragmentIndex=0;
    private static int prefragment=0;
    private ImageView tab_home,tab_troup,tab_local,tab_chat,tab_me;
    private AlertDialog dialog;
    private AlertDialog dialog_login;
    private EditText phone_num_txt,phone_pass_txt;
    private View loginview;
    private MeFragment meFragment;
    private static Login login;
    private static Login.DataBean.UserBean userBean;
    private TextView denglu;
    private DbManager db;
//    public AMapLocationClient mLocationClient = null;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        db = x.getDb(new DbManager.DaoConfig());
        try {
            userBean=db.selector(Login.DataBean.UserBean.class).findFirst();
            if (userBean!=null) {
                DrukApp.setIsDengLu(true);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginview = LayoutInflater.from(this).inflate(R.layout.login, null);
        manager=getSupportFragmentManager();
        //高德
//        mLocationClient = new AMapLocationClient(getApplicationContext());
        dialog=new AlertDialog.Builder(this)
                .setView(LayoutInflater.from(this).inflate(R.layout.welcome,null))
                .create();
        dialog_login=new AlertDialog.Builder(this)
                .setView(loginview)
                .create();
        meFragment=new MeFragment();
        initView();
        setData();
        setLinstener();
        LogUtils.out("定位测试：","经度2："+DrukApp.getLatitude()+"维度2："+DrukApp.getLongitude());
    }

    private void setLinstener() {

    }

    private void setData() {

        fragList=new ArrayList<>();
        fragList.add(new HomeFragment());
        fragList.add(new ShareFragment());
        fragList.add(new LocalFragment());
        fragList.add(new CharFragment());
        fragList.add(meFragment);
        transaction=manager.beginTransaction();
        transaction.add(R.id.mainFrag,fragList.get(0));
        transaction.commit();

    }

    private void initView() {
        tab_home = (ImageView) findViewById(R.id.tab_home);
        tab_troup = (ImageView) findViewById(R.id.tab_tourpic);
        tab_local = (ImageView) findViewById(R.id.tab_local);
        tab_chat = (ImageView) findViewById(R.id.tab_chat);
        tab_me = (ImageView) findViewById(R.id.tab_me);
        denglu= (TextView) findViewById(R.id.w_btn_login);
        phone_num_txt= (EditText) loginview.findViewById(R.id.phone_num_txt);
        phone_pass_txt= (EditText) loginview.findViewById(R.id.phone_pass_txt);
    }

    public void tabFragment(int fragmentIndex){
        if(currentfragmentIndex!=fragmentIndex){
            manager=getSupportFragmentManager();
            transaction=manager.beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            Fragment formFragment=fragList.get(currentfragmentIndex);
            Fragment toFragment=fragList.get(fragmentIndex);
            if(!toFragment.isAdded()){
                transaction.hide(formFragment).add(R.id.mainFrag,toFragment);
            }else{
                transaction.hide(formFragment).show(toFragment);
            }
//                transaction.addToBackStack(null);
            transaction.commit();
            currentfragmentIndex=fragmentIndex;
        }
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab_home_l:
                tab_home.setBackgroundResource(R.drawable.tab_home_focused);
                tab_troup.setBackgroundResource(R.drawable.tab_tourpic_normal);
                tab_local.setBackgroundResource(R.drawable.tab_local_normal);
                tab_chat.setBackgroundResource(R.drawable.tab_chat_normal);
                tab_me.setBackgroundResource(R.drawable.tab_me_normal);
                tabFragment(0);
                break;
            case R.id.tab_tourpic_l:
                tab_home.setBackgroundResource(R.drawable.tab_home_normal);
                tab_troup.setBackgroundResource(R.drawable.tab_tourpic_focused);
                tab_local.setBackgroundResource(R.drawable.tab_local_normal);
                tab_chat.setBackgroundResource(R.drawable.tab_chat_normal);
                tab_me.setBackgroundResource(R.drawable.tab_me_normal);
                tabFragment(1);
                break;
            case R.id.tab_local_l:
                tab_home.setBackgroundResource(R.drawable.tab_home_normal);
                tab_troup.setBackgroundResource(R.drawable.tab_tourpic_normal);
                tab_local.setBackgroundResource(R.drawable.tab_local_focused);
                tab_chat.setBackgroundResource(R.drawable.tab_chat_normal);
                tab_me.setBackgroundResource(R.drawable.tab_me_normal);
                tabFragment(2);
                break;
            case R.id.tab_chat_l:
                tab_home.setBackgroundResource(R.drawable.tab_home_normal);
                tab_troup.setBackgroundResource(R.drawable.tab_tourpic_normal);
                tab_local.setBackgroundResource(R.drawable.tab_local_normal);
                tab_chat.setBackgroundResource(R.drawable.tab_chat_focused);
                tab_me.setBackgroundResource(R.drawable.tab_me_normal);
                if (DrukApp.isDengLu()){

                }else {
                    dialog.show();
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.welcome_bg);
                }
                tabFragment(3);
                break;
            case R.id.tab_me_l:
                tab_home.setBackgroundResource(R.drawable.tab_home_normal);
                tab_troup.setBackgroundResource(R.drawable.tab_tourpic_normal);
                tab_local.setBackgroundResource(R.drawable.tab_local_normal);
                tab_chat.setBackgroundResource(R.drawable.tab_chat_normal);
                tab_me.setBackgroundResource(R.drawable.tab_me_focused);
                if (DrukApp.isDengLu()){

                }else {
                    dialog.show();
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.welcome_bg);
                }
                tabFragment(4);
                break;
            case R.id.me_information: //个人信息
                if (DrukApp.isDengLu()){
                    Intent intent = new Intent(this, Me_information_Activity.class);
//                intent.putExtra("login",login);

                    Bundle data=new Bundle();
                    data.putSerializable("login",userBean);
                    intent.putExtras(data);
                    startActivity(intent);
                }else {
                    dialog.show();
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.welcome_bg);
                }

                break;
            case R.id.w_btn_login: //登录
                dialog_login.show();
                dialog_login.getWindow().setBackgroundDrawableResource(R.drawable.welcome_bg);
                dialog.cancel();
                break;
            case R.id.w_btn_zhuce: //注册
                break;
            case R.id.w_btn_wj: //忘记密码
                break;
            case R.id.w_btn_qr: //确认登录
                String phome_num = null,pass = null;
                if (!TextUtils.isEmpty(phone_num_txt.getText())) {
                    phome_num = phone_num_txt.getText().toString();
                }else {
                    Toast.makeText(this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                }
                if (!TextUtils.isEmpty(phone_pass_txt.getText())) {
                    pass = phone_pass_txt.getText().toString();
                }else {
                    Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }
                Base_persenter persenter=new Login_persenterimpl(MainActivity.this);
                persenter.setData(phome_num,pass);
                break;
            case R.id.w_btn_blck:
                dialog.cancel();
                tabFragment(0);
                break;
            case 999999: //暂定于清除登录信息
                try {
                    db.delete(Login.DataBean.UserBean.class, WhereBuilder.b("_id","=",userBean.getUuid()));
                } catch (DbException e) {
                    Toast.makeText(MainActivity.this,"厉害了我的用户，操作失败了耶",Toast.LENGTH_LONG).show();
                }
                tabFragment(0);
                break;
            default:
                break;
        }

    }
    public void me_click(View view){
        switch (view.getId()){

            case R.id.me_myEvent:
                startActivity(new Intent(this, Me_myEvent_Activity.class));
                break;
            case R.id.me_corpuscle:
                startActivity(new Intent(this, Me_corpusde_Activity.class));
                break;
            case R.id.me_invitation:
                startActivity(new Intent(this, Me_invitation_Activity.class));
                break;
            case R.id.me_notes:
                startActivity(new Intent(this, Me_notes_Activity.class));
                break;
            case R.id.me_setting:
                startActivity(new Intent(this, Me_setting_Activity.class));
                break;

            case R.id.me_money:
                Toast.makeText(this,"您拥有"+userBean.getCoins()+"个达客币",Toast.LENGTH_SHORT).show();
                break;
        }
    }



    @Override
    public void login(Object obj) {
        login= (Login) obj;
        dialog_login.cancel();
        meFragment.login();
//        tabFragment(prefragment);
//        onClick(denglu);
//        DrukApp.setIsDengLu(true);



//        tabFragment(4);
//        Toast.makeText(this,login.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        DrukApp.setIsDengLu(false);
    }
}
