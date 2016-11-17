package cn.duckr.duckr.persenter;

import cn.duckr.duckr.bean.Home_HuoDong;
import cn.duckr.duckr.model.Home_model;
import cn.duckr.duckr.model.Home_modelimpl;
import cn.duckr.duckr.view.Base_view;

/**
 * Created by Dizner on 2016/11/11.
 */

public class Base_persenterimpl implements Base_persenter<Object> {
    private Home_model model=new Home_modelimpl(this);
    private Base_view view;
    private Home_HuoDong.DataBean data;

    public Base_persenterimpl(Base_view view) {
        this.view = view;
//        model=new Home_modelimpl();
    }

    @Override
    public void getData(Object dataBean) {
//        data = dataBean;
        view.loadData(dataBean);
    }

    @Override
    public void setData(String ... indexStr) {
        model.getData(indexStr);
    }
}
