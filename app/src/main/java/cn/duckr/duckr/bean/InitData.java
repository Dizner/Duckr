package cn.duckr.duckr.bean;

/**
 * Created by Dizner on 2016/11/13.
 */

public class InitData {

    /**
     * Status : 0
     * Data : {"ProvinceId":610000,"CityId":610100,"CityName":"西安市","CityShortName":"西安","IsOpen":0,"CreateTime":"2016-09-09 18:07:17","UpdateTime":"2016-09-09 18:07:17"}
     * Msg : 设置应用数据成功
     */

    private int Status;
    private DataBean Data;
    private String Msg;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public static class DataBean {
        /**
         * ProvinceId : 610000
         * CityId : 610100
         * CityName : 西安市
         * CityShortName : 西安
         * IsOpen : 0
         * CreateTime : 2016-09-09 18:07:17
         * UpdateTime : 2016-09-09 18:07:17
         */

        private int ProvinceId;
        private int CityId;
        private String CityName;
        private String CityShortName;
        private int IsOpen;
        private String CreateTime;
        private String UpdateTime;

        public int getProvinceId() {
            return ProvinceId;
        }

        public void setProvinceId(int ProvinceId) {
            this.ProvinceId = ProvinceId;
        }

        public int getCityId() {
            return CityId;
        }

        public void setCityId(int CityId) {
            this.CityId = CityId;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }

        public String getCityShortName() {
            return CityShortName;
        }

        public void setCityShortName(String CityShortName) {
            this.CityShortName = CityShortName;
        }

        public int getIsOpen() {
            return IsOpen;
        }

        public void setIsOpen(int IsOpen) {
            this.IsOpen = IsOpen;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }
    }
}
