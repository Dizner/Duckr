package cn.duckr.duckr.model;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.duckr.duckr.DrukApp;
import cn.duckr.duckr.LogUtils;
import cn.duckr.duckr.bean.InitData;
import cn.duckr.duckr.persenter.Base_persenter;

/**
 * Created by Dizner on 2016/11/11.
 */

public class Init_modelimpl implements Home_model{


//    private static String indexStr="";
    private Base_persenter persenter;
    public Init_modelimpl(Base_persenter persenter) {
        this.persenter = persenter;
    }

    @Override
    public void getData(String ...indexStr) {
        String url="http://api.duckr.cn/api/v6/app/config/set/";
        RequestParams entity=new RequestParams(url);
        entity.addBodyParameter("ClientId", "03388835b34f9a57174905cefeccf561");
        entity.addBodyParameter("Imei", "867875029741516");
        entity.addBodyParameter("MacAddr", "00%3A9a%3Acd%3Abf%3A80%3Add&ScreenRes=1080x1776");
        entity.addBodyParameter("CpuType", "armv7l");
        entity.addBodyParameter("DeviceName", "iPhone-8s");
        entity.addBodyParameter("DeviceModel", "");
        entity.addBodyParameter("AppChannel", "wandoujia.com");
        entity.addHeader("Cookie","DeviceType=2; AppVer=3.0.2; CID=05a7b812206300c0fe009bdcc1560d64; UUID=78c9d50829ac0216ccf276417765852d; DeviceUUID=00000000-2ff3-457e-ffff-ffff951720df; Token=6f7592aff8cf81622f94dd47c61acae6; VUID=3772db0052f60bccbd77ff837ad9338f; IDFA=867875029741516; CityId=610100; LocLng="+ DrukApp.getLongitude()+"; LocLat="+ DrukApp.getLatitude()+";");
        LogUtils.out("网络","结果");
        x.http().post(entity, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                LogUtils.out("网络定位信息","结果");
                Gson gson=new Gson();
                InitData huoDong = gson.fromJson(result, InitData.class);
//                indexStr=huoDong.getData().getOrderStr();
//                LogUtils.out("Bean",indexStr);
                persenter.getData(huoDong);
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
