package cn.duckr.duckr.persenter;

import cn.duckr.duckr.LogUtils;
import cn.duckr.duckr.bean.Share_local;
import cn.duckr.duckr.model.Home_model;
import cn.duckr.duckr.model.Remen_modelimpl;
import cn.duckr.duckr.view.ReMen_view;

/**
 * Created by Dizner on 2016/11/15.
 */

public class ReMen_persenter implements Base_persenter<Share_local.DataBean> {

    private Home_model model=new Remen_modelimpl(this);
    private ReMen_view view;
    private Share_local.DataBean data;

    public ReMen_persenter(ReMen_view view) {
        this.view = view;
//        model=new Home_modelimpl();
    }

    @Override
    public void getData(Share_local.DataBean dataBean) {
        LogUtils.out("同城网络","同城测试结果-P层");
        view.loadData(dataBean);
    }

    @Override
    public void setData(String... indexStr) {
        model.getData(indexStr);
    }
}
