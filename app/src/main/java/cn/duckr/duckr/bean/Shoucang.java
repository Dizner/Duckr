package cn.duckr.duckr.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Dizner on 2016/11/15.
 */
@Table(name = "userShoucang")
public class Shoucang {
    @Column(name = "userId")
    String userId;
    @Column(name = "dataName")
    String dataName;
    @Column(name = "dataType")
    int dataType;
    @Column(name = "_id",isId = true)
    String url;
    @Column(name = "time")
    String time;

    public Shoucang(String userId, String dataName, int dataType, String url, String time) {
        this.userId = userId;
        this.dataName = dataName;
        this.dataType = dataType;
        this.url = url;
        this.time = time;
    }

    public Shoucang() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
