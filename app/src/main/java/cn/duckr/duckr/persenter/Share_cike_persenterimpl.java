package cn.duckr.duckr.persenter;

import cn.duckr.duckr.model.Home_model;
import cn.duckr.duckr.model.Share_cike_modelimpl;
import cn.duckr.duckr.view.Share_cike_view;

/**
 * Created by Dizner on 2016/11/11.
 */

public class Share_cike_persenterimpl implements Base_persenter<Object> {
    private Home_model model=new Share_cike_modelimpl(this);
    private Share_cike_view view;

    public Share_cike_persenterimpl(Share_cike_view view) {
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

    public void get2Data(Object dataBean){

    }
}
