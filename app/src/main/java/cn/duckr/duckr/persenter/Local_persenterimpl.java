package cn.duckr.duckr.persenter;

import cn.duckr.duckr.model.Home_model;
import cn.duckr.duckr.model.Local_modelimpl;
import cn.duckr.duckr.view.Local_view;

/**
 * Created by Dizner on 2016/11/11.
 */

public class Local_persenterimpl implements Base_persenter<Object> {
    private Home_model model=new Local_modelimpl(this);
    private Local_view view;

    public Local_persenterimpl(Local_view view) {
        this.view = view;
//        model=new Home_modelimpl();
    }

    @Override
    public void getData(Object dataBean) {
        view.loadData(dataBean);
    }

    @Override
    public void setData(String ... indexStr) {
        model.getData(indexStr);
    }
}
