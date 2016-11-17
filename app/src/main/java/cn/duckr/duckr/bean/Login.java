package cn.duckr.duckr.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Dizner on 2016/11/13.
 */
public class Login {


    /**
     * Status : 0
     * Data : {"User":{"Uuid":"78c9d50829ac0216ccf276417765852d","Cid":"05a7b812206300c0fe009bdcc1560d64","Telephone":"18893721878","ChatAccount":"78c9d50829ac0216ccf276417765852d@127.0.0.1","ChatPassword":"39814862","Nick":"Dizner","AvatarUrl":"http://download.duckr.cn/avatar_29485c9e4c277f00fdcd104bcab92548","ThumbAvatarUrl":"http://download.duckr.cn/avatar_29485c9e4c277f00fdcd104bcab92548?imageView2/2/w/200","Gender":1,"Birthday":"1995-01-01","Age":21,"Stature":0,"LiveCityId":110000,"LiveCityName":"北京市","School":"","Industry":"","Duty":"","Salary":"","Hobby":"","Signature":"","Coins":50,"IsNameAuth":0,"Type":1,"RecentVisitNum":0,"OrderNum":0,"TicketNum":0,"PhotoNum":0,"InviteNum":0,"FocusNum":0,"FansNum":0,"UpdateTime":"2016-11-12 22:01:23"},"FocusType":0,"Distance":0}
     * Msg : 登录成功
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
         * User : {"Uuid":"78c9d50829ac0216ccf276417765852d","Cid":"05a7b812206300c0fe009bdcc1560d64","Telephone":"18893721878","ChatAccount":"78c9d50829ac0216ccf276417765852d@127.0.0.1","ChatPassword":"39814862","Nick":"Dizner","AvatarUrl":"http://download.duckr.cn/avatar_29485c9e4c277f00fdcd104bcab92548","ThumbAvatarUrl":"http://download.duckr.cn/avatar_29485c9e4c277f00fdcd104bcab92548?imageView2/2/w/200","Gender":1,"Birthday":"1995-01-01","Age":21,"Stature":0,"LiveCityId":110000,"LiveCityName":"北京市","School":"","Industry":"","Duty":"","Salary":"","Hobby":"","Signature":"","Coins":50,"IsNameAuth":0,"Type":1,"RecentVisitNum":0,"OrderNum":0,"TicketNum":0,"PhotoNum":0,"InviteNum":0,"FocusNum":0,"FansNum":0,"UpdateTime":"2016-11-12 22:01:23"}
         * FocusType : 0
         * Distance : 0
         */

        private UserBean User;
        private int FocusType;
        private int Distance;

        public UserBean getUser() {
            return User;
        }

        public void setUser(UserBean User) {
            this.User = User;
        }

        public int getFocusType() {
            return FocusType;
        }

        public void setFocusType(int FocusType) {
            this.FocusType = FocusType;
        }

        public int getDistance() {
            return Distance;
        }

        public void setDistance(int Distance) {
            this.Distance = Distance;
        }
        @Table(name = "USER")
        public static class UserBean implements Serializable {
            /**
             * Uuid : 78c9d50829ac0216ccf276417765852d
             * Cid : 05a7b812206300c0fe009bdcc1560d64
             * Telephone : 18893721878
             * ChatAccount : 78c9d50829ac0216ccf276417765852d@127.0.0.1
             * ChatPassword : 39814862
             * Nick : Dizner
             * AvatarUrl : http://download.duckr.cn/avatar_29485c9e4c277f00fdcd104bcab92548
             * ThumbAvatarUrl : http://download.duckr.cn/avatar_29485c9e4c277f00fdcd104bcab92548?imageView2/2/w/200
             * Gender : 1
             * Birthday : 1995-01-01
             * Age : 21
             * Stature : 0
             * LiveCityId : 110000
             * LiveCityName : 北京市
             * School :
             * Industry :
             * Duty :
             * Salary :
             * Hobby :
             * Signature :
             * Coins : 50
             * IsNameAuth : 0
             * Type : 1
             * RecentVisitNum : 0
             * OrderNum : 0
             * TicketNum : 0
             * PhotoNum : 0
             * InviteNum : 0
             * FocusNum : 0
             * FansNum : 0
             * UpdateTime : 2016-11-12 22:01:23
             */
            @Column(name = "_id",isId = true)
            private String Uuid;
            @Column(name = "Cid")
            private String Cid;
            @Column(name = "Telephone")
            private String Telephone;
            @Column(name = "ChatAccount")
            private String ChatAccount;
            @Column(name = "ChatPassword")
            private String ChatPassword;
            @Column(name = "Nick")
            private String Nick;
            @Column(name = "AvatarUrl")
            private String AvatarUrl;
            @Column(name = "ThumbAvatarUrl")
            private String ThumbAvatarUrl;
            @Column(name = "Gender")
            private int Gender;
            @Column(name = "Birthday")
            private String Birthday;
            @Column(name = "Age")
            private int Age;
            @Column(name = "Stature")
            private int Stature;
            @Column(name = "LiveCityId")
            private int LiveCityId;
            @Column(name = "LiveCityName")
            private String LiveCityName;
            @Column(name = "School")
            private String School;
            @Column(name = "Industry")
            private String Industry;
            @Column(name = "Duty")
            private String Duty;
            @Column(name = "Salary")
            private String Salary;
            @Column(name = "Hobby")
            private String Hobby;
            @Column(name = "Signature")
            private String Signature;
            @Column(name = "Coins")
            private int Coins;
            @Column(name = "IsNameAuth")
            private int IsNameAuth;
            @Column(name = "Type")
            private int Type;
            @Column(name = "RecentVisitNum")
            private int RecentVisitNum;
            @Column(name = "OrderNum")
            private int OrderNum;
            @Column(name = "TicketNum")
            private int TicketNum;
            @Column(name = "PhotoNum")
            private int PhotoNum;
            @Column(name = "InviteNum")
            private int InviteNum;
            @Column(name = "FocusNum")
            private int FocusNum;
            @Column(name = "FansNum")
            private int FansNum;
            @Column(name = "UpdateTime")
            private String UpdateTime;

            public String getUuid() {
                return Uuid;
            }

            public void setUuid(String Uuid) {
                this.Uuid = Uuid;
            }

            public String getCid() {
                return Cid;
            }

            public void setCid(String Cid) {
                this.Cid = Cid;
            }

            public String getTelephone() {
                return Telephone;
            }

            public void setTelephone(String Telephone) {
                this.Telephone = Telephone;
            }

            public String getChatAccount() {
                return ChatAccount;
            }

            public void setChatAccount(String ChatAccount) {
                this.ChatAccount = ChatAccount;
            }

            public String getChatPassword() {
                return ChatPassword;
            }

            public void setChatPassword(String ChatPassword) {
                this.ChatPassword = ChatPassword;
            }

            public String getNick() {
                return Nick;
            }

            public void setNick(String Nick) {
                this.Nick = Nick;
            }

            public String getAvatarUrl() {
                return AvatarUrl;
            }

            public void setAvatarUrl(String AvatarUrl) {
                this.AvatarUrl = AvatarUrl;
            }

            public String getThumbAvatarUrl() {
                return ThumbAvatarUrl;
            }

            public void setThumbAvatarUrl(String ThumbAvatarUrl) {
                this.ThumbAvatarUrl = ThumbAvatarUrl;
            }

            public int getGender() {
                return Gender;
            }

            public void setGender(int Gender) {
                this.Gender = Gender;
            }

            public String getBirthday() {
                return Birthday;
            }

            public void setBirthday(String Birthday) {
                this.Birthday = Birthday;
            }

            public int getAge() {
                return Age;
            }

            public void setAge(int Age) {
                this.Age = Age;
            }

            public int getStature() {
                return Stature;
            }

            public void setStature(int Stature) {
                this.Stature = Stature;
            }

            public int getLiveCityId() {
                return LiveCityId;
            }

            public void setLiveCityId(int LiveCityId) {
                this.LiveCityId = LiveCityId;
            }

            public String getLiveCityName() {
                return LiveCityName;
            }

            public void setLiveCityName(String LiveCityName) {
                this.LiveCityName = LiveCityName;
            }

            public String getSchool() {
                return School;
            }

            public void setSchool(String School) {
                this.School = School;
            }

            public String getIndustry() {
                return Industry;
            }

            public void setIndustry(String Industry) {
                this.Industry = Industry;
            }

            public String getDuty() {
                return Duty;
            }

            public void setDuty(String Duty) {
                this.Duty = Duty;
            }

            public String getSalary() {
                return Salary;
            }

            public void setSalary(String Salary) {
                this.Salary = Salary;
            }

            public String getHobby() {
                return Hobby;
            }

            public void setHobby(String Hobby) {
                this.Hobby = Hobby;
            }

            public String getSignature() {
                return Signature;
            }

            public void setSignature(String Signature) {
                this.Signature = Signature;
            }

            public int getCoins() {
                return Coins;
            }

            public void setCoins(int Coins) {
                this.Coins = Coins;
            }

            public int getIsNameAuth() {
                return IsNameAuth;
            }

            public void setIsNameAuth(int IsNameAuth) {
                this.IsNameAuth = IsNameAuth;
            }

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }

            public int getRecentVisitNum() {
                return RecentVisitNum;
            }

            public void setRecentVisitNum(int RecentVisitNum) {
                this.RecentVisitNum = RecentVisitNum;
            }

            public int getOrderNum() {
                return OrderNum;
            }

            public void setOrderNum(int OrderNum) {
                this.OrderNum = OrderNum;
            }

            public int getTicketNum() {
                return TicketNum;
            }

            public void setTicketNum(int TicketNum) {
                this.TicketNum = TicketNum;
            }

            public int getPhotoNum() {
                return PhotoNum;
            }

            public void setPhotoNum(int PhotoNum) {
                this.PhotoNum = PhotoNum;
            }

            public int getInviteNum() {
                return InviteNum;
            }

            public void setInviteNum(int InviteNum) {
                this.InviteNum = InviteNum;
            }

            public int getFocusNum() {
                return FocusNum;
            }

            public void setFocusNum(int FocusNum) {
                this.FocusNum = FocusNum;
            }

            public int getFansNum() {
                return FansNum;
            }

            public void setFansNum(int FansNum) {
                this.FansNum = FansNum;
            }

            public String getUpdateTime() {
                return UpdateTime;
            }

            public void setUpdateTime(String UpdateTime) {
                this.UpdateTime = UpdateTime;
            }
        }
    }
}
