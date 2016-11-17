package cn.duckr.duckr.model;

import cn.duckr.duckr.bean.OrderEntity;

/**
 * Created by Dizner on 2016/11/13.
 */

public interface Alipay_model {
    void pay(OrderEntity orderEntity);
}
