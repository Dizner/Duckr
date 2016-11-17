package cn.duckr.duckr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.duckr.duckr.bean.WanLe_info;
import cn.duckr.duckr.listener.OnClickGoPhoto;
import cn.duckr.duckr.utils.CircleImageView;
import cn.duckr.duckr.utils.DIYUtils;

public class WanLeActivity extends AppCompatActivity {
    //    @ViewInject(R.id.wanle_pinglun)
//    private ListView wanle_pinglun;
    private LinearLayout wanle_pinglun_users_img;
    private GridLayout wanle_imgs;
    private ImageView yy_user_img,wanle_video_img;
    private TextView home_yy_username, home_yy_user_type, home_yy_user_age, home_yy_content, home_yy_datecount, wanle_count;
    private WanLe_info leInfo;
    private RelativeLayout relayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_le);
//        x.view().inject(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        leInfo = (WanLe_info) bundle.getSerializable("leInfo");
//        head= (LinearLayout) LayoutInflater.from(this).inflate(R.layout.wanle_info_head,null);
//        wanle_pinglun.addHeaderView(head);
        initView();
    }

    private void initView() {
        wanle_pinglun_users_img = (LinearLayout) findViewById(R.id.wanle_pinglun_users_img);
        wanle_imgs = (GridLayout) findViewById(R.id.wanle_imgs);
        yy_user_img = (ImageView) findViewById(R.id.yy_user_img);
        home_yy_username = (TextView) findViewById(R.id.home_yy_username);
        home_yy_user_type = (TextView) findViewById(R.id.home_yy_user_type);
        home_yy_user_age = (TextView) findViewById(R.id.home_yy_user_age);
        home_yy_content = (TextView) findViewById(R.id.home_yy_content);
        home_yy_datecount = (TextView) findViewById(R.id.home_yy_datecount);
        wanle_count = (TextView) findViewById(R.id.wanle_count);
        wanle_imgs = (GridLayout) findViewById(R.id.wanle_imgs);
        relayout= (RelativeLayout) findViewById(R.id.relayout);
        wanle_video_img= (ImageView) findViewById(R.id.wanle_video_img);
        setHeadData();
    }

    private void setHeadData() {
        WanLe_info.DataBean.CreateUserWrapperBean.UserBean user = leInfo.getData().getCreateUserWrapper().getUser();
        ImageOptions options = new ImageOptions.Builder()
                .setConfig(Bitmap.Config.ARGB_8888)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setUseMemCache(true)
                .build();
        x.image().bind(yy_user_img, user.getThumbAvatarUrl());
        home_yy_username.setText(user.getNick());
        home_yy_user_type.setText(user.getIndustry() + (user.getDuty().equals("") ? "" : "|" + user.getDuty()));
        home_yy_user_age.setText(user.getAge() + "");
        if (user.getGender() == 1) {
            home_yy_user_age.setBackgroundResource(R.drawable.boy);
        } else {
            home_yy_user_age.setBackgroundResource(R.drawable.girl);
        }
        home_yy_content.setText(leInfo.getData().getPhoto().getContent());
        wanle_count.setText("评论(" + leInfo.getData().getLikeList().size() + ")");
        String time = leInfo.getData().getPhoto().getCreateTime();
        home_yy_datecount.setText(DIYUtils.dateDiff(time) + "|" + leInfo.getData().getPhoto().getPlaceName());
        ArrayList<String> photoUrls = (ArrayList<String>) leInfo.getData().getPhoto().getPhotoUrls();
        int type = leInfo.getData().getPhoto().getType();
        WindowManager wm1 = this.getWindowManager();
        int width1 = wm1.getDefaultDisplay().getWidth();
        int height1 = wm1.getDefaultDisplay().getHeight();
        if (type != 2) {
            relayout.setVisibility(View.GONE);
            for (int i = 0; i < photoUrls.size(); i++) {
                ImageView iv = new ImageView(this);
                //获取当前屏幕宽度

                if (photoUrls.size() > 2) {
                    wanle_imgs.setColumnCount(3);
                    wanle_imgs.setRowCount(3);
                    iv.setLayoutParams(new RelativeLayout.LayoutParams(width1 / 3, width1 / 3));
                } else if (photoUrls.size() > 1) {
                    wanle_imgs.setColumnCount(3);
                    wanle_imgs.setRowCount(2);

                    iv.setLayoutParams(new RelativeLayout.LayoutParams(width1 / 2, width1 / 2));
                } else {
                    wanle_imgs.setColumnCount(1);
                    wanle_imgs.setRowCount(1);
                    iv.setLayoutParams(new RelativeLayout.LayoutParams(width1, width1));
                }

//            iv.setPadding(DIYUtils.dip2px(WanLeActivity.this,2),DIYUtils.dip2px(WanLeActivity.this,2),DIYUtils.dip2px(WanLeActivity.this,2),DIYUtils.dip2px(WanLeActivity.this,2));
//            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                x.image().bind(iv, photoUrls.get(i), options);
                iv.setOnClickListener(new OnClickGoPhoto(WanLeActivity.this, photoUrls, i));
//            wanle_imgs.se
                wanle_imgs.addView(iv, i);
            }
        }else {
            //视频播放位置控制
            relayout.setVisibility(View.VISIBLE);
            final VideoView iv=new VideoView(WanLeActivity.this);
//            final VideoView iv= (VideoView) findViewById(R.id.wanle_videos);
            iv.setVideoPath(photoUrls.get(0));
            iv.setLayoutParams(new RelativeLayout.LayoutParams(width1, width1));
            wanle_imgs.setColumnCount(1);
            wanle_imgs.setRowCount(1);
            wanle_imgs.addView(iv, 0);
            iv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (!mp.isPlaying()) {
                        mp.start();
                    }
                }
            });
            if (DrukApp.isWif()) {
                wanle_video_img.setVisibility(View.GONE);
                iv.start();
            }
            wanle_video_img.setVisibility(View.VISIBLE);
            wanle_video_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.start();
                }
            });
            wanle_imgs.setOnClickListener(new OnClickGoPhoto(WanLeActivity.this, photoUrls,type==2?true:false));

        }
        List<WanLe_info.DataBean.LikeListBean> likeList = leInfo.getData().getLikeList();
        for (int i = 0; i < likeList.size(); i++) {
            CircleImageView iv = new CircleImageView(this);
            x.image().bind(iv, likeList.get(i).getThumbAvatarUrl());
            iv.setLayoutParams(new RelativeLayout.LayoutParams(DIYUtils.dip2px(WanLeActivity.this, 20), DIYUtils.dip2px(WanLeActivity.this, 20)));
            wanle_pinglun_users_img.addView(iv, i);
        }
    }

    public void black(View view) {
        finish();
    }
}
