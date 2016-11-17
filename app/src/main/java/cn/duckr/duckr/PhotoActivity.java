package cn.duckr.duckr;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.duckr.duckr.adapter.PhotoPagerAdapter;

public class PhotoActivity extends AppCompatActivity{
    @ViewInject(R.id.photo_content)
    private ViewPager photo_content;
    @ViewInject(R.id.photo_count)
    private TextView photo_count;
    private List<View> view;
    private ImageOptions options;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        x.view().inject(this);
        Intent intent=getIntent();
        Bundle bundle = intent.getExtras();
        ArrayList<String> url = bundle.getStringArrayList("url");
        int tag = intent.getIntExtra("tag", 0);
        boolean type = intent.getBooleanExtra("type", false);
        WindowManager wm1 = this.getWindowManager();
        int width1 = wm1.getDefaultDisplay().getWidth();
        int height1 = wm1.getDefaultDisplay().getHeight();
        view=new  ArrayList<>();

        options=new ImageOptions.Builder()
                .setLoadingDrawableId(R.drawable.default_place)
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                .build();
        if (type) {
            VideoView iv=new VideoView(PhotoActivity.this);
            iv.setVideoPath(url.get(0));
            iv.setLayoutParams(new RelativeLayout.LayoutParams(width1, width1));
            iv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (!mp.isPlaying()) {
                        mp.start();
                    }
                }
            });
            view.add(iv);
            iv.start();
        }else{
            for (int i = 0; i < url.size(); i++) {
                imgView= new ImageView(this);
//            ZoomImageView v= new ZoomImageView(this);
                imgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                x.image().bind(imgView, url.get(i), options);
                view.add(imgView);
            }
        }

        photo_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                photo_count.setText(position+1+"/"+view.size());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        photo_content.setAdapter(new PhotoPagerAdapter(view,PhotoActivity.this));
        photo_content.setCurrentItem(tag);
    }

}
