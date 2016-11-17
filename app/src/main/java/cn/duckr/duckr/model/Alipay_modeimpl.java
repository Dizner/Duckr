package cn.duckr.duckr.model;

import cn.duckr.duckr.bean.OrderEntity;

/**
 * Created by Dizner on 2016/11/13.
 */

public class Alipay_modeimpl implements Alipay_model {


    // 商户PID 商户的签约 ID 必须 2088 开头
    public static final String PARTNER = "2088102181331778";
    // 商户收款账号 支付宝账号
    public static final String SELLER = "cvuvxo0226@sandbox.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "sw537LrHC/6eQEGEd35LCZ/5WDTWogyRhbS2B0jKvAcITC+M5YzDyOkeg84mkiy87c+rRlI0S6GZYxz7q7Of+Yiv4hSeM5ZkwzqIGwNFqdhXEsZHOZGV+POO+hOXpGnvM0P1nXr/1vQNEhiKy8HlTETYXieMUs4PLQ+cR1onNwk=";
    @Override
    public void pay(OrderEntity orderEntity) {
        final String orderInfo = getOrderInfo(orderEntity.getOrderProductName(), //商品名称
                orderEntity.getOrderProductNameList() //商品详情
                ,orderEntity.getOrderPrice(), //价格
                orderEntity.getOrderNum()); //订单单号；与App服务要保持一致  // 订单信息
        //对订单进行签名
//        String sign= SignUtils.sign(orderInfo,RSA_PRIVATE);

        //对签名的数据进行URL编码
//        sign= URLEncoder.encode(sign,"utf-8");

        //最后生成支付宝规范的订单
//        final String alipayOrderInfo= orderInfo+"&sign=\""+sign+"\""+"&sign_type=\"RSA\"";
//
//        Runnable payRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                PayTask alipay = new PayTask(Home_HD_Content_Activity.this);
//                String result = alipay.payV2(orderInfo,true);
//
//                Message msg = new Message();
//                msg.what = SDK_PAY_FLAG;
//                msg.obj = result;
//                mHandler.sendMessage(msg);
//            }
//        };
        // 必须异步调用
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
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
