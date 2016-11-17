package cn.duckr.duckr.model;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.duckr.duckr.DrukApp;
import cn.duckr.duckr.LogUtils;
import cn.duckr.duckr.bean.Home_YaoYue;
import cn.duckr.duckr.persenter.Base_persenter;

/**
 * Created by Dizner on 2016/11/12.
 */

public class Home_YaoYue_modelimpl implements Home_model{


    private Base_persenter persenter;
    public Home_YaoYue_modelimpl(Base_persenter persenter) {
        this.persenter = persenter;
    }

    @Override
    public void getData(String ...indexStr) {
        String url="http://api.duckr.cn/api/v6/home/invite/list/";
        RequestParams entity=new RequestParams(url);
        entity.addBodyParameter("ThemeId", "0");
        entity.addBodyParameter("GenderLimit", "0");
        entity.addBodyParameter("OrderType", "0");
        entity.addBodyParameter("OrderStr", indexStr[0]);
        entity.addHeader("Cookie","DeviceType=2; AppVer=3.0.2; CID=05a7b812206300c0fe009bdcc1560d64; UUID=78c9d50829ac0216ccf276417765852d; DeviceUUID=00000000-2ff3-457e-ffff-ffff951720df; Token=6f7592aff8cf81622f94dd47c61acae6; VUID=3772db0052f60bccbd77ff837ad9338f; IDFA=867875029741516; CityId=610100; LocLng="+ DrukApp.getLongitude()+"; LocLat="+ DrukApp.getLatitude()+";");
        LogUtils.out("网络","结果");
        x.http().post(entity, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                LogUtils.out("网络999","结果");
                Gson gson=new Gson();
                Home_YaoYue yaoYue = gson.fromJson(result, Home_YaoYue.class);
                persenter.getData(yaoYue);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.out("网络999","错误信息"+ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.out("网络999","错误信息"+cex);
            }

            @Override
            public void onFinished() {
            }
        });
    }
}
