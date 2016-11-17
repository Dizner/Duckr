package cn.duckr.duckr.persenter;

import cn.duckr.duckr.bean.OrderEntity;

/**
 * Created by Dizner on 2016/11/16.
 */

public interface AliPayPersenter<E> {
    void setData(E result);
    void getData(OrderEntity orderEntity);
}
