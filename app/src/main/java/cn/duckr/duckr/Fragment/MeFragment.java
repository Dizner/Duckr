package cn.duckr.duckr.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import cn.duckr.duckr.DrukApp;
import cn.duckr.duckr.LogUtils;
import cn.duckr.duckr.R;
import cn.duckr.duckr.bean.Login;


/**
 * Created by PF on 2016/11/8.
 */

public class MeFragment extends Fragment{

    private View v;
    private int moneysize;
    private ImageView me_user_img,me_sex_image;
    private TextView me_name,me_age,me_money_size;
    private DbManager db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (v==null) {
            v = inflater.inflate(R.layout.me_fragment,container,false);
            DbManager.DaoConfig config=new DbManager.DaoConfig();
            db = x.getDb(config);
            initView();

        }

        return v;
    }


    private void initView() {

        me_money_size = (TextView) v.findViewById(R.id.me_money_size);
        me_user_img= (ImageView) v.findViewById(R.id.me_user_img);
        me_sex_image= (ImageView) v.findViewById(R.id.me_sex_image);
        me_name= (TextView) v.findViewById(R.id.me_name);
        me_age= (TextView) v.findViewById(R.id.me_age);
        me_money_size= (TextView) v.findViewById(R.id.me_money_size);


    }
    @Override
    public void onResume() {
        super.onResume();
        if (DrukApp.isDengLu()) {
           login();
        }
    }
    public void login() {
        DrukApp.setIsDengLu(true);
        Login.DataBean.UserBean user = null;
        try {
            user = db.selector(Login.DataBean.UserBean.class).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        x.image().bind(me_user_img,user.getAvatarUrl());
        if (user.getGender()==1) {
            me_sex_image.setImageResource(R.drawable.boy);
        }else{
            me_sex_image.setImageResource(R.drawable.girl);
        }
        me_name.setText(user.getNick());
        me_age.setText(user.getAge()+"");
        me_money_size.setText(user.getCoins()+"");

        if (user!=null) {
            LogUtils.out("调用99999测试","测试结果");
            Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();
        }

    }
}
