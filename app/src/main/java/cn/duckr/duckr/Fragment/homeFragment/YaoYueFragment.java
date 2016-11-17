package cn.duckr.duckr.Fragment.homeFragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.duckr.duckr.R;
import cn.duckr.duckr.adapter.Home_List_Adapter2;
import cn.duckr.duckr.bean.Home_YaoYue;
import cn.duckr.duckr.listener.OnListUpdateData;
import cn.duckr.duckr.persenter.Base_persenter;
import cn.duckr.duckr.persenter.Home_YaoYue_persenterimpl;
import cn.duckr.duckr.view.Home_YaoYue_view;

/**
 * Created by Dizner on 2016/11/8.
 */

public class YaoYueFragment extends Fragment implements Home_YaoYue_view<Home_YaoYue> {
    private ListView home_yy_list;
    private List<Home_YaoYue.DataBean.InviteWrapperListBean> list;
    private static String indexStr = "";
    private SwipeRefreshLayout home_yy_update;
    private Home_List_Adapter2 adapter;
    private LinearLayout foot_view;
    private ProgressBar seek;
    private View view;
    private ImageButton fba_yy;
    private AlertDialog dialog;
    private WindowManager wm1;
    private int width1;
    private int height1;
    private RelativeLayout rt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.home_yaoyue_pager, container, false);
            wm1 = getActivity().getWindowManager();
            width1 = wm1.getDefaultDisplay().getWidth();
            height1 = wm1.getDefaultDisplay().getHeight();
            initView(view);
            setData();
        }
        return view;
    }

    private void setData() {
        list = new ArrayList<>();

        home_yy_update.setRefreshing(true);
        list = new ArrayList<>();

        adapter = new Home_List_Adapter2(list, getActivity());
        home_yy_list.setAdapter(adapter);
        getData();
    }

    private void getData() {
        Base_persenter persenter = new Home_YaoYue_persenterimpl(this);
        persenter.setData(indexStr);
    }

    private void initView(View view) {
        rt = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.home_hd_sousuo_diag, null);
        rt.setLayoutParams(new RelativeLayout.LayoutParams(width1, height1 / 2));
        dialog = new AlertDialog.Builder(getActivity())
                .setView(rt)
                .create();
        home_yy_list = (ListView) view.findViewById(R.id.home_yy_list);
        home_yy_update = (SwipeRefreshLayout) view.findViewById(R.id.home_yy_update);
        foot_view = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.list_foot, null);
        seek = (ProgressBar) foot_view.findViewById(R.id.seekBar);
        fba_yy = (ImageButton) view.findViewById(R.id.fba_yy);
        home_yy_list.addFooterView(foot_view);
        home_yy_list.setOnScrollListener(new OnListUpdateData(getActivity()) {
            @Override
            public void upDate() {
                getData();
                seek.setVisibility(View.VISIBLE);
            }
        });
        home_yy_update.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list = new ArrayList<>();
                adapter = new Home_List_Adapter2(list, getActivity());
                indexStr = "";
                getData();
            }
        });

        final ObjectAnimator animator1 = ObjectAnimator.ofFloat(dialog, "translationY", -height1, height1 / 2);
        animator1.setDuration(1000);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());

        fba_yy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                //dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
                lp.width = width1;
                lp.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialogWindow.setAttributes(lp);
                animator1.start();
            }
        });
    }

    @Override
    public void loadData(Home_YaoYue obj) {
        list.addAll(obj.getData().getInviteWrapperList());
        adapter.notifyDataSetChanged();
        home_yy_update.setRefreshing(false);
        seek.setVisibility(View.GONE);
    }


}
