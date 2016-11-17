package cn.duckr.duckr.Fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.duckr.duckr.DrukApp;
import cn.duckr.duckr.LogUtils;
import cn.duckr.duckr.R;
import cn.duckr.duckr.adapter.Home_List_Adapter2;
import cn.duckr.duckr.bean.Home_YaoYue;
import cn.duckr.duckr.bean.InitData;
import cn.duckr.duckr.listener.OnListUpdateData;
import cn.duckr.duckr.persenter.Base_persenter;
import cn.duckr.duckr.persenter.Init_persenterimpl;
import cn.duckr.duckr.persenter.Local_persenterimpl;
import cn.duckr.duckr.view.Init_view;
import cn.duckr.duckr.view.Local_view;

/**
 * Created by Dizner on 2016/11/8.
 */

public class LocalFragment extends Fragment implements Animator.AnimatorListener, Local_view<Home_YaoYue>,Init_view {

    private ListView localList;
    private View view;
    private List<Home_YaoYue.DataBean.InviteWrapperListBean> infoList;
    private boolean mIsTitleHide = false;
    private boolean mIsAnim = false;
    private float lastX = 0;
    private float lastY = 0;
    private RelativeLayout local_title;
    private RelativeLayout title_content;
    private boolean isHidden=false;
    private ImageButton fba;
    private SwipeRefreshLayout local_updata;
    private Home_List_Adapter2 adapter;
    private String indexStr="";
    private TextView city;
    private LinearLayout foot_view;
    private ProgressBar seek;
    private static boolean isDi=true;
    private RelativeLayout load_img;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.local_pager,container,false);
        initView();
        initData();
        setData();
        return view;
    }

    private void initView() {
        localList= (ListView) view.findViewById(R.id.localList);
        load_img= (RelativeLayout) view.findViewById(R.id.load_img);
        local_updata= (SwipeRefreshLayout) view.findViewById(R.id.local_updata);
        local_title= (RelativeLayout) view.findViewById(R.id.local_title);
        title_content= (RelativeLayout) view.findViewById(R.id.title_content);
        city= (TextView) view.findViewById(R.id.local_city);
        fba= (ImageButton) view.findViewById(R.id.fba);
        foot_view= (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.list_foot,null);
        seek= (ProgressBar) foot_view.findViewById(R.id.seekBar);
        localList.addFooterView(foot_view);
        localList.setOnScrollListener(new OnListUpdateData(getActivity()) {
            @Override
            public void upDate() {
                getData();
                seek.setVisibility(View.VISIBLE);
            }
        });
        local_updata.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setData();
                isDi=true;
                LogUtils.out("定位测试：","经度2："+ DrukApp.getLatitude()+"维度2："+DrukApp.getLongitude());
            }
        });
    }
    private boolean isDown = false;
    private boolean isUp = false;
    private void initData() {

        infoList=new ArrayList<>();
        localList.setAdapter(adapter);
        localList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (mIsAnim) {
                    return false;
                }
                final int action = event.getAction();
                float x = event.getX();
                float y = event.getY();
                LogUtils.out("ListView滑动","滑动"+event.getY());
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        lastY = y;
                        lastX = x;
                        return false;
                    case MotionEvent.ACTION_MOVE:
                        float dY = Math.abs(y - lastY);
                        float dX = Math.abs(x - lastX);
                        //Y大于lastY说明当前Y值大于按下时的Y值，即向下滑动的动作
                        boolean down = y > lastY ? true : false;
                        lastY = y;
                        lastX = x;
                        isUp = dX < 8 && dY > 8 && !mIsTitleHide && !down ;
                        isDown = dX < 8 && dY > 4 && mIsTitleHide && down;

                        if (isUp) {

                            LogUtils.out("ListView滑动","向上滑动");
                            LogUtils.out("Title高度向上",""+local_title.getHeight());
                            View view = title_content;
                            float[] f = new float[2];
                            f[0] = 0;
                            f[1] = -view.getHeight();
                            ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationY",f);
                            animator1.setInterpolator(new AccelerateDecelerateInterpolator());
                            animator1.setDuration(100);
                            animator1.start();
                            ObjectAnimator animator2 = ObjectAnimator.ofFloat(fba, "translationY",f);
                            animator2.setInterpolator(new AccelerateDecelerateInterpolator());
                            animator2.setDuration(100);
                            animator2.start();
                            animator2.addListener(LocalFragment.this);
                            isHidden=true;
                        } else if (isDown) {
                            LogUtils.out("ListView滑动","向下滑动");
                            LogUtils.out("Title高度向下",""+local_title.getHeight());
                            View view = title_content;
                            float[] f = new float[2];
                            f[0] = -view.getHeight();
                            f[1] = 0;
                            ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationY",f);
                            animator1.setDuration(200);
                            animator1.setInterpolator(new AccelerateDecelerateInterpolator());
                            animator1.start();
                            animator1.addListener(LocalFragment.this);
                            ObjectAnimator animator2 = ObjectAnimator.ofFloat(fba, "translationY",f);
                            animator2.setDuration(100);
                            animator2.setInterpolator(new AccelerateDecelerateInterpolator());
                            animator2.start();
                            isHidden=false;
                        } else {
                            return false;
                        }
                        mIsTitleHide = !mIsTitleHide;
                        mIsAnim = true;
                        break;
                    default:
                        return false;
                }


                return false;
            }
        });
    }

    private void setData() {
        local_updata.setRefreshing(true);

        adapter=new Home_List_Adapter2(infoList,getActivity());
        localList.setAdapter(adapter);
        getData();
    }

    private void getData() {
        Base_persenter persenter1=new Local_persenterimpl(this);
        Base_persenter persenter2=new Init_persenterimpl(this);
        persenter1.setData(indexStr);
        persenter2.setData(indexStr);
    }

    @Override
    public void loadData(Home_YaoYue obj) {
        infoList.addAll(obj.getData().getInviteWrapperList());
        adapter.notifyDataSetChanged();
        indexStr=obj.getData().getOrderStr();
        local_updata.setRefreshing(false);
        load_img.setVisibility(View.GONE);
        seek.setVisibility(View.GONE);
    }

    public void setMarginTop(int page){
        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        layoutParam.setMargins(0, page, 0, 0);
        local_updata.setLayoutParams(layoutParam);
        local_updata.invalidate();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if(isHidden){
            setMarginTop(-title_content.getHeight());
        }else{
            setMarginTop(0);
        }
        mIsAnim = false;
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    @Override
    public void initData(Object obj) {
        InitData d= (InitData) obj;
        String cityShortName = d.getData().getCityShortName();
        toPush(cityShortName);
        city.setText(cityShortName);
    }
    private void toPush(String cityShortName){
        if (isDi) {
            Toast.makeText(getActivity(),"定位成功,当前城市："+cityShortName,Toast.LENGTH_LONG).show();
            isDi=false;
        }
    }
}
