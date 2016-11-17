package cn.duckr.duckr.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.duckr.duckr.LogUtils;
import cn.duckr.duckr.R;
import cn.duckr.duckr.WanLeActivity;
import cn.duckr.duckr.bean.Share_local;
import cn.duckr.duckr.bean.WanLe_info;
import cn.duckr.duckr.listener.OnClickGoPhoto;
import cn.duckr.duckr.utils.DIYUtils;

/**
 * Created by Dizner on 2016/11/11.
 */

public class Share_local_Adapter extends BaseAdapter {
    private List<Share_local.DataBean.PhotoWrapperListBean> list;
    private Activity context;
    private ImageView tc_photo3_img1;
    private ImageView tc_photo3_img2;
    private ImageView tc_photo3_img3;

    public Share_local_Adapter(List<Share_local.DataBean.PhotoWrapperListBean> list, Activity context) {
        this.list = list;
        this.context = context;
        LogUtils.out("同城网络","同城测试结果-P层-构造器:"+list.size());
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
//        if (convertView==null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.tongcheng_content_pager,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
//        }else{
//            holder= (ViewHolder) convertView.getTag();
//        }
        onBindViewHolder(holder,position);
//        convertView.setContentDescription(list.get(position).getShareUrl());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://api.duckr.cn/api/v6/photo/detail/" + list.get(position).getPhoto().getPuid();
                x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson=new Gson();
                        WanLe_info leInfo = gson.fromJson(result, WanLe_info.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("leInfo",leInfo);
                        context.startActivity(new Intent(context, WanLeActivity.class).putExtras(bundle));
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(context,"网络跑丢了，无法进入",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }
        });
        LogUtils.out("同城网络","同城测试结果-P层-Adapter-onCreate:"+list.size());
        return convertView;
    }

    class ViewHolder{
        private ImageView share_tc_img;
        private GridLayout share_tc_imgs;
        private TextView share_tc_user_type,share_tc_username,share_tc_user_age,share_tc_content,share_tc_datecount,share_tc_pinglun,share_tc_xingqu;
        public ViewHolder(View itemView) {
            share_tc_img= (ImageView) itemView.findViewById(R.id.share_tc_img);
            share_tc_user_type= (TextView) itemView.findViewById(R.id.share_tc_user_type);
            share_tc_username= (TextView) itemView.findViewById(R.id.share_tc_username);
            share_tc_user_age= (TextView) itemView.findViewById(R.id.share_tc_user_age);
            share_tc_content= (TextView) itemView.findViewById(R.id.share_tc_content);
            share_tc_datecount= (TextView) itemView.findViewById(R.id.share_tc_datecount);
            share_tc_pinglun= (TextView) itemView.findViewById(R.id.share_tc_pinglun);
            share_tc_xingqu= (TextView) itemView.findViewById(R.id.share_tc_xingqu);
            share_tc_imgs= (GridLayout) itemView.findViewById(R.id.share_tc_imgs);
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Share_local.DataBean.PhotoWrapperListBean t = list.get(position);
        Share_local.DataBean.PhotoWrapperListBean.CreateUserWrapperBean.UserBean user = t.getCreateUserWrapper().getUser();
        Share_local.DataBean.PhotoWrapperListBean.PhotoBean photo = t.getPhoto();
        List<String> photoUrls = photo.getPhotoUrls();
        ImageOptions options = new ImageOptions.Builder()
                .setConfig(Bitmap.Config.ARGB_8888)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.default_place)
                .setUseMemCache(true)
                .build();
        x.image().bind(holder.share_tc_img,user.getThumbAvatarUrl(),options);
        WindowManager wm1 = context.getWindowManager();
        int width1 = wm1.getDefaultDisplay().getWidth();
        int height1 = wm1.getDefaultDisplay().getHeight();

        for (int i = 0; i < photoUrls.size(); i++) {
            ImageView iv = new ImageView(context);
            //获取当前屏幕宽度
            if (photoUrls.size() > 4) {
                holder.share_tc_imgs.setColumnCount(3);
                holder.share_tc_imgs.setRowCount(3);
                iv.setLayoutParams(new RelativeLayout.LayoutParams(width1 / 3, width1 / 3));
            } else if (photoUrls.size() == 4 || photoUrls.size() == 3) {
                holder.share_tc_imgs.setColumnCount(2);
                holder.share_tc_imgs.setRowCount(2);
                iv.setLayoutParams(new RelativeLayout.LayoutParams(width1/2, width1/2));
            } else if (photoUrls.size() == 2){
                holder.share_tc_imgs.setColumnCount(2);
                holder.share_tc_imgs.setRowCount(2);
                iv.setLayoutParams(new RelativeLayout.LayoutParams(width1/2, width1/2));
            } else if (photoUrls.size() == 1){
                holder.share_tc_imgs.setColumnCount(1);
                holder.share_tc_imgs.setRowCount(1);
                iv.setLayoutParams(new RelativeLayout.LayoutParams(width1, width1));
            }

            x.image().bind(iv, photoUrls.get(i), options);
            iv.setOnClickListener(new OnClickGoPhoto(context, (ArrayList<String>) photoUrls, i));
            holder.share_tc_imgs.addView(iv, i);
        }

        holder.share_tc_username.setText(user.getNick());
        String age = user.getAge() + "";
        if (age.length()>1) {
            holder.share_tc_user_age.setText(age.substring(age.length()-2));
        }
        holder.share_tc_user_age.setText(age);
        if (user.getGender()==1) {
            holder.share_tc_user_age.setBackgroundResource(R.drawable.boy);
        }else {
            holder.share_tc_user_age.setBackgroundResource(R.drawable.girl);
        }
        holder.share_tc_user_type.setText(user.getDuty());
//        holder.home_yy_target.setText(ac.getDepartName()+" → "+ac.getDestNames());
//        //格式化日期
        String time = photo.getCreateTime();
        String week = DIYUtils.getWeekByDateStr(time.substring(0, 10));
        StringBuffer date=new StringBuffer(time.substring(5, 10).replaceFirst("\\-","月"));
//                .append(time.substring(11, 16)+"开始");
        holder.share_tc_datecount.setText(date.toString()+"|"+photo.getPlaceName());
        holder.share_tc_content.setText(photo.getContent());
        LogUtils.out("内容",photo.getContent());

        holder.share_tc_pinglun.setText(photo.getCommentNum()+"");
        holder.share_tc_xingqu.setText(photo.getLikeNum()+"");
//        int stime = Integer.parseInt(ac.getCreateTime().substring(8, 10));
//        int etime = Integer.parseInt(ac.getUpdateTime().substring(8,10));
//        if (etime-stime<2) {
//            holder.home_yy_datecount.setText("昨天 "+ac.getCreateTime().substring(11,16)+" | "+ac.getPublishPlace());
//        }else if(etime-stime<4){
//            holder.home_yy_datecount.setText(etime-stime+"天前 | "+ac.getPublishPlace());
//        }else {
//            holder.home_yy_datecount.setText(ac.getCreateTime().substring(5,10)+" | "+ac.getPublishPlace());
//        }


    }

}
