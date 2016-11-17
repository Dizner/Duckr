package cn.duckr.duckr;

//import com.phone1000.days48xutilspaymvpbase64.bean.OrderEntity;
//import com.phone1000.days48xutilspaymvpbase64.presenter.IPresenter;
//
//import java.net.URLEncoder;
//
//import alipay.sdk.pay.utils.PayResult;
//import alipay.sdk.pay.utils.SignUtils;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import com.alipay.sdk.app.PayTask;

import java.net.URLEncoder;
import java.util.Map;

import cn.duckr.duckr.AliPay.PayResult;
import cn.duckr.duckr.AliPay.SignUtils;
import cn.duckr.duckr.bean.OrderEntity;
import cn.duckr.duckr.persenter.AliPayPersenter;

/**
 * Created by james on 16/11/10.
 */
public class AlipayService {
//
    // 商户PID 商户的签约 ID 必须 2088 开头
    public static final String PARTNER = "2088111278561763";
    // 商户收款账号 支付宝账号
    public static final String SELLER = "gaoyandingzhi@126.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANEClM9ja39OuhbiFcPYG8nUt19TIGvnBjC2CGMV3BKY2pTolVuicMfM0yyxvwtewe7Wkk+06Zl8fjgIWZS8SsfOeznQZbJq236CbcFYIhDsorDllDwQ0Uk409WSjaOCDJamOjGeQjYqy3D7v+z+Z48ZvCOPleX2h415mHQeHWVdAgMBAAECgYB6FrHqOr7uTIRzHXltPu1shi7fJeWIYhjBl3NqvbghvNvho8KrFkYez8yDDQj1kVJjOz+YA6t4lrn77RS2xw4+fRJgBy/LD9ILectaThysuFt84yKooSuFAv1AQKMeVXkpnFuzzBFtxyuRPtPUYXftSvEm/9BapFHGEVCuT7RvAQJBAP9yq18VFhPQAfngld9n0NwmCO33kdbFYqVIWBNKZdvVZIqwIvnmTqsgQacrvWutsWauukKT7VzySkht/uE63j0CQQDRdjgqx4H7SfMjkaZK5nJ6ptuFgR19HkakOJZSIM78Ot3PzfHcnfYuCRjs8lIEWmhYqj2FE+BcZ9cejphGuTWhAkB0XimBXBq9ldGAonXD2whDcbQ5q8EtJKgmgUlWKFs0hQaTQ1/7lZYa0Mv3uq5EwlCBZXGGaNsFr351dl5Y/jdFAkA6D2DmSsL22rqwo1DK9jHJWbMDwJRh+CBwqNbSERIOzGprjZR7KLXycMcd9tVRK5Y87YN7/dR1CLuSVshS4kfBAkAW6ls9/RlBA6gOpDuq+Qn4CZUng3h7OJsDgzCY95RtuMISJNuVFcGC/XVKB+urkyfhR/H7I8HIPXQtNJenH9f2";


    private Activity mActivity;

    AliPayPersenter<PayResult> iPresenter;
    private Handler mHandler =new Handler(Looper.getMainLooper());

    public AlipayService(AliPayPersenter<PayResult> iPresenter, Activity mActivity) {
        this.iPresenter= iPresenter;
        this.mActivity = mActivity;
    }

    public void pay(OrderEntity orderEntity) throws Exception{

        String orderInfo = getOrderInfo(orderEntity.getOrderProductName(), //商品名称
                orderEntity.getOrderProductNameList() //商品详情
                ,orderEntity.getOrderPrice(), //价格
                orderEntity.getOrderNum()); //订单单号；与App服务要保持一致

        //对订单进行签名
        String sign= SignUtils.sign(orderInfo,RSA_PRIVATE);

        //对签名的数据进行URL编码
        sign= URLEncoder.encode(sign,"utf-8");

        //最后生成支付宝规范的订单
        final String alipayOrderInfo= orderInfo+"&sign=\""+sign+"\""+"&sign_type=\"RSA\"";
        //发起支付
        new Thread(){
            @Override
            public void run() {
                //检查账号是否存在
                PayTask alipay = new PayTask(mActivity);

//                if(payTask.checkAccountIfExist()){

                final Map<String, String> result = alipay.payV2(alipayOrderInfo, true);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            PayResult resul= new PayResult(result);
                            iPresenter.setData(resul); //必须保存Presenter是在主线程中执行
                        }
                    });

//                }else{ //账号不存在
//
//                    mHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(mActivity,"当前收款账号不存在,不能发起支付",Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
            }
        }.start();


    }
    /**
     * create the order info. 创建订单信息
     *
     */
    private String getOrderInfo(String subject, String body, int price,String orderNo) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + orderNo+ "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + 0.01f + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm"
                + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

}
