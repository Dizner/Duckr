package cn.duckr.duckr.persenter;

/**
 * Created by Dizner on 2016/11/11.
 */

public interface Base_persenter<E> {
    void getData(E e);
    void setData(String ...indexStr);
}
