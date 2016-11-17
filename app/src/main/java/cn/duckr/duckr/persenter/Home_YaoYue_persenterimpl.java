package cn.duckr.duckr.persenter;

import cn.duckr.duckr.model.Home_YaoYue_modelimpl;
import cn.duckr.duckr.model.Home_model;
import cn.duckr.duckr.view.Home_YaoYue_view;

/**
 * Created by Dizner on 2016/11/11.
 */

public class Home_YaoYue_persenterimpl implements Base_persenter<Object> {
    private Home_model model=new Home_YaoYue_modelimpl(this);
    private Home_YaoYue_view view;

    public Home_YaoYue_persenterimpl(Home_YaoYue_view view) {
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
