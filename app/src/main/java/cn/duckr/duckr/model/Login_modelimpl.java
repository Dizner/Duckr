package cn.duckr.duckr.model;

import com.google.gson.Gson;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.duckr.duckr.DrukApp;
import cn.duckr.duckr.LogUtils;
import cn.duckr.duckr.bean.Login;
import cn.duckr.duckr.persenter.Base_persenter;

/**
 * Created by Dizner on 2016/11/12.
 */

public class Login_modelimpl implements Home_model{


    private Base_persenter persenter;
    public Login_modelimpl(Base_persenter persenter) {
        this.persenter = persenter;
    }

    @Override
    public void getData(String ...info) {
        DbManager.DaoConfig cofig=new DbManager.DaoConfig();
        final DbManager db = x.getDb(cofig);
        String logurl="http://api.duckr.cn/api/v6/user/login/";
        RequestParams entity=new RequestParams(logurl);
        entity.addBodyParameter("Telephone",info[0]);
        entity.addBodyParameter("Password",info[1]);
        entity.addHeader("Cookie","DeviceType=2; AppVer=3.0.2; CID=05a7b812206300c0fe009bdcc1560d64; UUID=78c9d50829ac0216ccf276417765852d; DeviceUUID=00000000-2ff3-457e-ffff-ffff951720df; Token=6f7592aff8cf81622f94dd47c61acae6; VUID=3772db0052f60bccbd77ff837ad9338f; IDFA=867875029741516; CityId=610100; LocLng="+DrukApp.getLongitude()+"; LocLat="+ DrukApp.getLatitude()+";");
        x.http().post(entity, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                Login login = gson.fromJson(result, Login.class);
                LogUtils.out("登录结果",login.getMsg());


                Login.DataBean.UserBean user = login.getData().getUser();
                try {
                    db.saveOrUpdate(user);
                    LogUtils.out("写入","成功");
                    DrukApp.setUserID(user.getCid());
                    persenter.getData(login);
                } catch (DbException e) {
                    e.printStackTrace();
                    LogUtils.out("写入","失败");
                }
                DrukApp.setIsDengLu(true);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

}
