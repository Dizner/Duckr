package cn.duckr.duckr.persenter;

import cn.duckr.duckr.bean.InitData;
import cn.duckr.duckr.model.Home_model;
import cn.duckr.duckr.model.Init_modelimpl;
import cn.duckr.duckr.view.Init_view;

/**
 * Created by Dizner on 2016/11/11.
 */

public class Init_persenterimpl implements Base_persenter<Object> {
    private Home_model model=new Init_modelimpl(this);
    private Init_view view;
    private InitData.DataBean data;

    public Init_persenterimpl(Init_view view) {
        this.view = view;
//        model=new Home_modelimpl();
    }

    @Override
    public void getData(Object dataBean) {
//        data = dataBean;
        view.initData(dataBean);
    }

    @Override
    public void setData(String ... indexStr) {
        model.getData(indexStr);
    }
}
