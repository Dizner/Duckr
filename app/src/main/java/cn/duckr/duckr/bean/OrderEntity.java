package cn.duckr.duckr.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by james on 16/11/10.
 */

public class OrderEntity implements Parcelable {

    private String orderProductName; //发起支付时使用的名称
    private String orderProductNameList; //选择购物车中的商品名称列表
    private String orderNum; //订单的编号
    private int orderPrice; //订单的价格

    public String getOrderProductName() {
        return orderProductName;
    }

    public void setOrderProductName(String orderProductName) {
        this.orderProductName = orderProductName;
    }

    public String getOrderProductNameList() {
        return orderProductNameList;
    }

    public void setOrderProductNameList(String orderProductNameList) {
        this.orderProductNameList = orderProductNameList;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderProductName);
        dest.writeString(this.orderProductNameList);
        dest.writeString(this.orderNum);
        dest.writeInt(this.orderPrice);
    }

    public OrderEntity() {
    }

    public OrderEntity(String orderProductName, int orderPrice, String orderNum, String orderProductNameList) {
        this.orderProductName = orderProductName;
        this.orderPrice = orderPrice;
        this.orderNum = orderNum;
        this.orderProductNameList = orderProductNameList;
    }

    protected OrderEntity(Parcel in) {
        this.orderProductName = in.readString();
        this.orderProductNameList = in.readString();
        this.orderNum = in.readString();
        this.orderPrice = in.readInt();
    }

    public static final Creator<OrderEntity> CREATOR = new Creator<OrderEntity>() {
        @Override
        public OrderEntity createFromParcel(Parcel source) {
            return new OrderEntity(source);
        }

        @Override
        public OrderEntity[] newArray(int size) {
            return new OrderEntity[size];
        }
    };
}