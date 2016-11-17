package cn.duckr.duckr.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.image.ImageOptions;

import java.util.List;

import cn.duckr.duckr.R;

/**
 * Created by Dizner on 2016/11/12.
 */

public class PhotoPagerAdapter extends PagerAdapter {
    private List<View> list;
    private Activity activity;
    private ImageOptions options;

    public PhotoPagerAdapter(List<View> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        options=new ImageOptions.Builder()
                .setLoadingDrawableId(R.drawable.default_place)
                .build();
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        ImageView v= (ImageView) LayoutInflater.from(activity).inflate(R.layout.photo_item,null);
//        v.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        v.animate();
//        x.image().bind(v,list.get(position),options);
//        container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                activity.finish();
//                LogUtils.out("点击","是否点击");
//            }
//        });
        container.addView(list.get(position));
        return list.get(position);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));

    }
}
