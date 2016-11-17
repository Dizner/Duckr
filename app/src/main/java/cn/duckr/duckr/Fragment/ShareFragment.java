package cn.duckr.duckr.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.duckr.duckr.CameraPhotoActivity;
import cn.duckr.duckr.Fragment.shareFragment.CiKeFragment;
import cn.duckr.duckr.Fragment.shareFragment.ReMenFragment;
import cn.duckr.duckr.Fragment.shareFragment.TongChengFragment;
import cn.duckr.duckr.R;
import cn.duckr.duckr.TakeVideoActivity;
import cn.duckr.duckr.adapter.HomePagerAdapter;

/**
 * Created by Dizner on 2016/11/8.
 */

public class ShareFragment extends Fragment {
    private TabLayout share_title;
    private ImageView share_go,share_sousuo;
    private ViewPager vpShow_share;
    private String[] titleList=new String[]{"热门","此刻","同城"};
    private List<Fragment> contetnList=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        x.view().inject(getActivity());
        View v=inflater.inflate(R.layout.share_pager,container,false);

        initView(v);
        setData();
        return v;

    }

    private void setData() {
        contetnList=new ArrayList<>();
        contetnList.add(new ReMenFragment());
        contetnList.add(new CiKeFragment());
        contetnList.add(new TongChengFragment());
        vpShow_share.setAdapter(new HomePagerAdapter(getFragmentManager(),contetnList,titleList));
        vpShow_share.setCurrentItem(1);
    }

    private void initView(View v) {
        share_sousuo= (ImageView) v.findViewById(R.id.share_sousuo);
        share_go= (ImageView) v.findViewById(R.id.share_go);
        share_title= (TabLayout) v.findViewById(R.id.share_title);
        vpShow_share= (ViewPager) v.findViewById(R.id.vpShow_share);
        share_title.setTabMode(TabLayout.MODE_FIXED);
        share_title.setTabGravity(TabLayout.GRAVITY_FILL);
        share_title.setTabTextColors(Color.GRAY,Color.BLACK);
        share_title.setSelectedTabIndicatorColor(Color.rgb(255,217,00));
        share_title.setSelectedTabIndicatorHeight(10);
        //通过ViewPager的滑动来控制标题的字体大小
//        vpShow_home.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                Log.d("4444444444444444",""+scrollX);
//                int size=scrollX/100+20;
//            }
//        });
        share_title.setupWithViewPager(vpShow_share);
        share_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TakeVideoActivity.class));
            }
        });
        share_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CameraPhotoActivity.class));
            }
        });
    }
}
