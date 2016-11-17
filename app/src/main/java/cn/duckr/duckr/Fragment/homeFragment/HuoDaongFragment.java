package cn.duckr.duckr.Fragment.homeFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.duckr.duckr.LogUtils;
import cn.duckr.duckr.R;
import cn.duckr.duckr.adapter.Home_Hd_Head_3_Adapter;
import cn.duckr.duckr.adapter.Home_List_Adapter;
import cn.duckr.duckr.adapter.Home_Hd_Head_2_Adapter;
import cn.duckr.duckr.bean.Home_HuoDong;
import cn.duckr.duckr.listener.OnClickGoWeb;
import cn.duckr.duckr.listener.OnListUpdateData;
import cn.duckr.duckr.persenter.Base_persenter;
import cn.duckr.duckr.persenter.Base_persenterimpl;
import cn.duckr.duckr.utils.DIYUtils;
import cn.duckr.duckr.view.Base_view;

/**
 * Created by Dizner on 2016/11/8.
 */

public class HuoDaongFragment extends Fragment implements Base_view {
    private ListView home_hd_list;
    private List<Home_HuoDong.DataBean.HotActivWrapperListBean> list;
    private List<Home_HuoDong.DataBean.HomeRcmdWrapperListBean.ActivWrapperListBean> list_head;
    private Home_List_Adapter adapter;
    private static String indexStr="";
    private SwipeRefreshLayout home_hd_update;
    private LinearLayout foot_view;
    private LinearLayout head_view_1;
    private LinearLayout head_view_2;
    private ProgressBar seek;
    private TextView reinfo;
    private RecyclerView home_hd_other;
    private Home_HuoDong.DataBean dataBean=null;
    private Home_Hd_Head_2_Adapter homeHdHead1Adapter =null;
    private RecyclerView home_hd_hot=null;
    private List<Home_HuoDong.DataBean.HomeRcmdWrapperListBean.ThemeListBean> themeList;
    private Home_Hd_Head_3_Adapter homeHdHead3Adapter;
    private RelativeLayout loadimg;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view==null) {
            view=inflater.inflate(R.layout.home_huodong_pager,container,false);
            loadimg= (RelativeLayout) view.findViewById(R.id.load_img);
            initView(view);
            setData(indexStr);
            loadDate();
        }
        return view;


    }

    private void initView(View view) {

        home_hd_list= (ListView) view.findViewById(R.id.home_hd_list);
        home_hd_update= (SwipeRefreshLayout) view.findViewById(R.id.home_hd_update);
        AbsListView.LayoutParams params_1= new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DIYUtils.dip2px(getActivity(),281));
        AbsListView.LayoutParams params_2= new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DIYUtils.dip2px(getActivity(),150));
        AbsListView.LayoutParams params_3= new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DIYUtils.dip2px(getActivity(),150));
        foot_view= (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.list_foot,null);
        head_view_1= (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.home_hd_head_1,null);
        head_view_1.setLayoutParams(params_1);
        head_view_1.setOnClickListener(new OnClickGoWeb(getActivity()));
        head_view_2= (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.home_hd_head_2,null);
        head_view_2.setLayoutParams(params_2);
        View head_view_3 =LayoutInflater.from(getContext()).inflate(R.layout.home_hd_head_3,null);
        home_hd_other= (RecyclerView) head_view_2.findViewById(R.id.home_hd_other);
        home_hd_other.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        home_hd_hot= (RecyclerView) head_view_3.findViewById(R.id.home_hd_hot);
        home_hd_hot.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        seek= (ProgressBar) foot_view.findViewById(R.id.seekBar);
        reinfo= (TextView) foot_view.findViewById(R.id.reinfo);
        home_hd_list.addHeaderView(head_view_1);
        home_hd_list.addHeaderView(head_view_2);
//        head_view_3.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DIYUtils.dip2px(getActivity(),40)));
        head_view_3.setLayoutParams(params_3);
        home_hd_list.addHeaderView(head_view_3);
        home_hd_list.addFooterView(foot_view);
    }

    private void setHead_1Data(LinearLayout head_view_1) {
        ImageView hd_list_img = (ImageView) head_view_1.findViewById(R.id.hd_list_img);
        ImageView home_hd_userimg_1= (ImageView) head_view_1.findViewById(R.id.home_hd_userimg_1);
        ImageView home_hd_userimg_2= (ImageView) head_view_1.findViewById(R.id.home_hd_userimg_2);
        ImageView home_hd_userimg_3= (ImageView) head_view_1.findViewById(R.id.home_hd_userimg_3);
        home_hd_userimg_1.setVisibility(View.GONE);
        home_hd_userimg_2.setVisibility(View.GONE);
        home_hd_userimg_3.setVisibility(View.GONE);
        TextView hd_title = (TextView) head_view_1.findViewById(R.id.hd_title);
        TextView hd_addr = (TextView) head_view_1.findViewById(R.id.hd_addr);
        TextView hd_time = (TextView) head_view_1.findViewById(R.id.hd_time);
        TextView hd_type = (TextView) head_view_1.findViewById(R.id.hd_type);
        TextView hd_pice = (TextView) head_view_1.findViewById(R.id.hd_picel);
        TextView hd_picetype = (TextView) head_view_1.findViewById(R.id.hd_piceltype);
        TextView home_hd_looks= (TextView) head_view_1.findViewById(R.id.home_hd_looks);
        Home_HuoDong.DataBean.HomeRcmdWrapperListBean.ActivWrapperListBean t = list_head.get(0);
        Home_HuoDong.DataBean.HomeRcmdWrapperListBean.ActivWrapperListBean.ActivBean ac = t.getActiv();
        List<Home_HuoDong.DataBean.HomeRcmdWrapperListBean.ActivWrapperListBean.DisplayUserListBean> userList = t.getDisplayUserList();
        x.image().bind(hd_list_img,ac.getPhotoUrls().get(0));
        switch (userList.size()) {
            case 0:
                break;
            case 1:
                x.image().bind(home_hd_userimg_1,userList.get(0).getThumbAvatarUrl());
                home_hd_userimg_1.setVisibility(View.VISIBLE);
                break;
            case 2:
                x.image().bind(home_hd_userimg_1,userList.get(0).getThumbAvatarUrl());
                home_hd_userimg_1.setVisibility(View.VISIBLE);
                x.image().bind(home_hd_userimg_2,userList.get(1).getThumbAvatarUrl());
                home_hd_userimg_2.setVisibility(View.VISIBLE);
                break;
            default:
                x.image().bind(home_hd_userimg_1,userList.get(0).getThumbAvatarUrl());
                home_hd_userimg_1.setVisibility(View.VISIBLE);
                x.image().bind(home_hd_userimg_2,userList.get(1).getThumbAvatarUrl());
                home_hd_userimg_2.setVisibility(View.VISIBLE);
                x.image().bind(home_hd_userimg_3,userList.get(2).getThumbAvatarUrl());
                home_hd_userimg_3.setVisibility(View.VISIBLE);
                break;
        }
        head_view_1.setContentDescription(t.getShareUrl());
        hd_title.setText(ac.getTitle());
        hd_addr.setText(ac.getGatherAddr());
        hd_time.setText(t.getCellTime());
        hd_type.setText(ac.getThemeTitleMain());
        hd_pice.setText(t.getPriceText());
        int type = t.getPriceType();
        hd_picetype.setText(type==1?"起/人":"/人");
        home_hd_looks.setText(t.getDisplayText());

    }

    private void loadDate() {
        home_hd_update.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                indexStr="";
                list=new ArrayList<>();
                setData(indexStr);
            }
        });
        home_hd_list.setOnScrollListener(new OnListUpdateData(getActivity()) {
            @Override
            public void upDate() {
                seek.setVisibility(View.VISIBLE);
                reinfo.setText("加载中...");
                getData(indexStr);
            }
        });
    }

    private void setData(String indexStr) {
        home_hd_update.setRefreshing(true);
        list=new ArrayList<>();
        list_head=new ArrayList<>();
        themeList=new ArrayList<>();
        homeHdHead3Adapter=new Home_Hd_Head_3_Adapter(themeList,getActivity());
        homeHdHead1Adapter =new Home_Hd_Head_2_Adapter(list_head,getActivity());
        home_hd_other.setAdapter(homeHdHead1Adapter);
        home_hd_hot.setAdapter(homeHdHead3Adapter);
        adapter=new Home_List_Adapter(list,getActivity());
        home_hd_list.setAdapter(adapter);
        getData(indexStr);
    }

    public void getData(String indexStr) {
        Base_persenter persenter=new Base_persenterimpl(this);
        persenter.setData(indexStr);
    }


    @Override
    public void loadData(Object data) {
        dataBean= (Home_HuoDong.DataBean) data;
        loadimg.setVisibility(View.GONE);
        List<Home_HuoDong.DataBean.HotActivWrapperListBean> list1 = dataBean.getHotActivWrapperList();
        indexStr=dataBean.getOrderStr();
        LogUtils.out("请求参数",indexStr);
        list.addAll(list1);
        Home_HuoDong.DataBean.HomeRcmdWrapperListBean bean = dataBean.getHomeRcmdWrapperList().get(2);
        themeList.addAll(bean.getThemeList()) ;
        list_head.addAll(dataBean.getHomeRcmdWrapperList().get(0).getActivWrapperList());
        homeHdHead1Adapter.notifyDataSetChanged();
        setHead_1Data(head_view_1);
        adapter.notifyDataSetChanged();
        home_hd_update.setRefreshing(false);
//                foot_view.setVisibility(View.GONE);
        seek.setVisibility(View.GONE);
        reinfo.setText("上拉加载更多");
    }

}
