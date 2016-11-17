package cn.duckr.duckr.persenter;

import cn.duckr.duckr.model.Home_model;
import cn.duckr.duckr.model.Login_modelimpl;
import cn.duckr.duckr.view.Login_view;

/**
 * Created by Dizner on 2016/11/11.
 */

public class Login_persenterimpl implements Base_persenter<Object> {
    private Home_model model=new Login_modelimpl(this);
    private Login_view view;

    public Login_persenterimpl(Login_view view) {
        this.view = view;
//        model=new Home_modelimpl();
    }

    @Override
    public void getData(Object dataBean) {
//        data = dataBean;
        view.login(dataBean);
    }

    @Override
    public void setData(String ...indexStr) {
        model.getData(indexStr[0],indexStr[1]);
    }
}
