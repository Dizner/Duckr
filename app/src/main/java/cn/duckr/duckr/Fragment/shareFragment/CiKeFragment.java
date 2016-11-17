package cn.duckr.duckr.Fragment.shareFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.etsy.android.grid.StaggeredGridView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.duckr.duckr.R;
import cn.duckr.duckr.adapter.Share_CikeAdapter;
import cn.duckr.duckr.bean.CiKe_data;
import cn.duckr.duckr.listener.OnListUpdateData;
import cn.duckr.duckr.persenter.Base_persenter;
import cn.duckr.duckr.persenter.Share_cike_persenterimpl;
import cn.duckr.duckr.view.Share_cike_view;

/**
 * Created by Dizner on 2016/11/8.
 */

public class CiKeFragment extends Fragment implements Share_cike_view<CiKe_data.DataBean> {
    private StaggeredGridView gridView;
    private List<CiKe_data.DataBean.PhotoWrapperListBean> list;
    private Share_CikeAdapter adapter=null;
    private static String indexStr="";
    private SwipeRefreshLayout share_cike_update;
    private View foot_view;
    private ProgressBar seek;
    private RelativeLayout load_img;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null) {
            view=inflater.inflate(R.layout.share_cike_pager,container,false);
            gridView= (StaggeredGridView) view.findViewById(R.id.share_cike_content);
            share_cike_update= (SwipeRefreshLayout) view.findViewById(R.id.share_cike_update);
            foot_view= (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.list_foot,null);
            load_img= (RelativeLayout) view.findViewById(R.id.load_img);
            seek= (ProgressBar) foot_view.findViewById(R.id.seekBar);
//        gridView.setNumColumns(2);
            //获取当前系统时间
            indexStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            setData();
        }

        return view;
    }

    private void setData() {
        gridView.addFooterView(foot_view);
        share_cike_update.setRefreshing(true);
        gridView.setOnScrollListener(new OnListUpdateData(getActivity()) {
            @Override
            public void upDate() {
                seek.setVisibility(View.VISIBLE);
                getData();
            }
        });
        list=new ArrayList<>();
        adapter=new Share_CikeAdapter(list,getActivity());
        share_cike_update.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list=new ArrayList<>();
                adapter=new Share_CikeAdapter(list,getActivity());
                indexStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                getData();
            }
        });
        getData();
    }

    @Override
    public void loadData(CiKe_data.DataBean obj) {
        list.addAll(obj.getPhotoWrapperList());
        gridView.setAdapter(adapter);
        indexStr=obj.getOrderStr();
        adapter.notifyDataSetChanged();
        share_cike_update.setRefreshing(false);
        load_img.setVisibility(View.GONE);
        seek.setVisibility(View.GONE);
    }

    public void getData() {
        Base_persenter persenter=new Share_cike_persenterimpl(this);
        persenter.setData(indexStr);
    }
}
