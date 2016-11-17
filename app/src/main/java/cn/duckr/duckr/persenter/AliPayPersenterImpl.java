package cn.duckr.duckr.persenter;

import android.app.Activity;

import cn.duckr.duckr.AliPay.PayResult;
import cn.duckr.duckr.AlipayService;
import cn.duckr.duckr.bean.OrderEntity;
import cn.duckr.duckr.view.Alipay_view;

/**
 * Created by Dizner on 2016/11/16.
 */

public class AliPayPersenterImpl implements AliPayPersenter {
    AlipayService service;
    Alipay_view view;
    private Activity mActivity;

    public AliPayPersenterImpl(Alipay_view view,Activity mActivity) {
        this.view = view;
        this.mActivity=mActivity;
        service=new AlipayService(this,mActivity);
    }

    @Override
    public void setData(Object result) {
        view.getDate((PayResult) result);
    }

    @Override
    public void getData(OrderEntity orderEntity) {
        try {
            service.pay(orderEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
