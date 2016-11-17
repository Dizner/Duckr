package cn.duckr.duckr.Fragment.shareFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import cn.duckr.duckr.LogUtils;
import cn.duckr.duckr.R;
import cn.duckr.duckr.adapter.Share_local_Adapter;
import cn.duckr.duckr.bean.Share_local;
import cn.duckr.duckr.listener.OnListUpdateData;
import cn.duckr.duckr.persenter.Base_persenter;
import cn.duckr.duckr.persenter.TongCheng_persenter;
import cn.duckr.duckr.view.TongCheng_view;

/**
 * Created by Dizner on 2016/11/8.
 */

public class TongChengFragment extends Fragment implements TongCheng_view<Share_local.DataBean> {
    private ListView tongcheng_list;
    private Share_local_Adapter adapter;
    private List<Share_local.DataBean.PhotoWrapperListBean> list;
    private String indexStr="";
    private LinearLayout foot_view;
    private ProgressBar seek;
    private SwipeRefreshLayout tongcheng_update;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null) {
            view=inflater.inflate(R.layout.tongcheng_pager,container,false);
            tongcheng_list= (ListView) view.findViewById(R.id.tongcheng_list);
            foot_view= (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.list_foot,null);
            tongcheng_update = (SwipeRefreshLayout) view.findViewById(R.id.tongcheng_update);
            seek= (ProgressBar) foot_view.findViewById(R.id.seekBar);
            tongcheng_list.addFooterView(foot_view);
            setData();
        }
        return view;
    }

    private void setData() {
        list=new ArrayList<>();
        adapter=new Share_local_Adapter(list,getActivity());
        tongcheng_update.setRefreshing(true);
        tongcheng_list.setAdapter(adapter);
        tongcheng_update.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                indexStr="";
                list=new ArrayList<>();
                adapter=new Share_local_Adapter(list,getActivity());
                getData();
            }
        });
        tongcheng_list.setOnScrollListener(new OnListUpdateData(getActivity()) {
            @Override
            public void upDate() {
                seek.setVisibility(View.VISIBLE);
                getData();
            }
        });
        getData();
    }

    @Override
    public void loadData(Share_local.DataBean obj) {
        LogUtils.out("同城网络","同城测试结果-V层");
        list.addAll(obj.getPhotoWrapperList());
        adapter.notifyDataSetChanged();
        indexStr=obj.getOrderStr();
        tongcheng_update.setRefreshing(false);
        seek.setVisibility(View.GONE);
        LogUtils.out("同城网络","同城测试结果-P层:"+list.size());
    }

    public void getData() {
        Base_persenter persenter=new TongCheng_persenter(this);
        persenter.setData(indexStr);
    }
}
