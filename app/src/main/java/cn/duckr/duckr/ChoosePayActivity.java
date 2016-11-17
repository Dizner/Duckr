package cn.duckr.duckr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import cn.duckr.duckr.AliPay.PayResult;
import cn.duckr.duckr.bean.OrderEntity;
import cn.duckr.duckr.persenter.AliPayPersenter;
import cn.duckr.duckr.persenter.AliPayPersenterImpl;
import cn.duckr.duckr.view.Alipay_view;

public class ChoosePayActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,Alipay_view {
    private CheckBox pay_wechat,pay_alipay;
    private static int payType=0;
    private AliPayPersenter persenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pay);
        pay_wechat= (CheckBox) findViewById(R.id.pay_wechat);
        pay_alipay= (CheckBox) findViewById(R.id.pay_alipay);
        setData();
    }

    private void setData() {
        persenter=new AliPayPersenterImpl(this,this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pay:
                switch (payType) {
                    case 0:
                        persenter.getData(new OrderEntity("测试商品",199,"201611161228","购物车2"));
                        break;
                    case 1:
                        break;
                }
                break;
            case R.id.pay_back:
                finish();
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.pay_wechat:
                payType=0;
                break;
            case R.id.pay_alipay:
                payType=1;
                break;
        }
    }

    @Override
    public void getDate(PayResult result) {
        String result1 = result.getResult();
        Toast.makeText(ChoosePayActivity.this,result1,Toast.LENGTH_LONG).show();
    }

    public void pay(View view) {

    }
}
