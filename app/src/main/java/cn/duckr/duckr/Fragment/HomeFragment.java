package cn.duckr.duckr.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.duckr.duckr.Fragment.homeFragment.HuoDaongFragment;
import cn.duckr.duckr.Fragment.homeFragment.YaoYueFragment;
import cn.duckr.duckr.R;
import cn.duckr.duckr.adapter.HomePagerAdapter;

/**
 * Created by Dizner on 2016/11/8.
 */

public class HomeFragment extends Fragment {
    private TabLayout home_title;
    private ViewPager vpShow_home;
    private String[] titleList=new String[]{"活动","邀约"};
    private List<Fragment> contetnList=null;
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        x.view().inject(getActivity());
        if (v==null) {
            v=inflater.inflate(R.layout.home_pager,container,false);

            initView(v);
            setData();
        }
        return v;

    }

    private void setData() {
        contetnList=new ArrayList<>();
        contetnList.add(new HuoDaongFragment());
        contetnList.add(new YaoYueFragment());
        vpShow_home.setAdapter(new HomePagerAdapter(getFragmentManager(),contetnList,titleList));
    }

    private void initView(View v) {
        home_title= (TabLayout) v.findViewById(R.id.home_title);
        vpShow_home= (ViewPager) v.findViewById(R.id.vpShow_home);
        home_title.setTabMode(TabLayout.MODE_FIXED);
        home_title.setTabGravity(TabLayout.GRAVITY_FILL);
        home_title.setTabTextColors(Color.GRAY,Color.BLACK);
        home_title.setSelectedTabIndicatorColor(Color.rgb(255,217,00));
        home_title.setSelectedTabIndicatorHeight(10);
        //通过ViewPager的滑动来控制标题的字体大小
//        vpShow_home.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                Log.d("4444444444444444",""+scrollX);
//                int size=scrollX/100+20;
//            }
//        });
        home_title.setupWithViewPager(vpShow_home);
    }
}
