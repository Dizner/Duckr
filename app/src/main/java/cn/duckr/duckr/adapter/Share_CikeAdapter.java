package cn.duckr.duckr.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import cn.duckr.duckr.R;
import cn.duckr.duckr.WanLeActivity;
import cn.duckr.duckr.bean.CiKe_data;
import cn.duckr.duckr.bean.WanLe_info;

/**
 * Created by Dizner on 2016/11/14.
 */

public class Share_CikeAdapter extends BaseAdapter {
    private List<CiKe_data.DataBean.PhotoWrapperListBean> list;
    private Context context;

    public Share_CikeAdapter(List<CiKe_data.DataBean.PhotoWrapperListBean> list, Context context) {
        this.list = list;
        this.context = context;
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
        if (convertView==null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.share_cike_item,parent,false);
            holder=new Share_CikeAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (Share_CikeAdapter.ViewHolder) convertView.getTag();
        }
//        convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,list.get(position).getPhoto().getHeight()));
        onBindViewHolder(holder,position);
        convertView.setContentDescription(list.get(position).getShareUrl());
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
        return convertView;
    }

//    @Override
//    public void loadData(WanLe_info obj) {
//
//    }


    class ViewHolder{
        private ImageView cike_img,cike_user_img,cike_video;
        private TextView cike_user_local,cike_content,cike_user_name,cike_dianzan_count,photo_count ;
        public ViewHolder(View itemView) {
            cike_img = (ImageView) itemView.findViewById(R.id.cike_img);
            cike_user_img= (ImageView) itemView.findViewById(R.id.cike_user_img);

            cike_user_local = (TextView) itemView.findViewById(R.id.cike_user_local);
            cike_content = (TextView) itemView.findViewById(R.id.cike_content);
            cike_user_name = (TextView) itemView.findViewById(R.id.cike_user_name);
            cike_dianzan_count = (TextView) itemView.findViewById(R.id.cike_dianzan_count);
            photo_count= (TextView) itemView.findViewById(R.id.photo_count);
            cike_video= (ImageView) itemView.findViewById(R.id.cike_video);
        }
    }

    public void onBindViewHolder(Share_CikeAdapter.ViewHolder holder, int position) {
        CiKe_data.DataBean.PhotoWrapperListBean t = list.get(position);
        ImageOptions options=new ImageOptions.Builder()
                .setConfig(Bitmap.Config.ARGB_8888)
                .build();
        //设置显示图片
        if (t.getPhoto().getType()==1) {
            x.image().bind(holder.cike_img,t.getPhoto().getThumbPhotoUrls().get(0),options);
            holder.cike_img.setAdjustViewBounds(true);
            holder.cike_video.setVisibility(View.GONE);
        }else if(t.getPhoto().getType()==2){
//            holder.cike_img.setVisibility(View.GONE);
//            holder.cike_video.setLayoutParams(new RelativeLayout.LayoutParams(DIYUtils.dip2px(context,250), DIYUtils.dip2px(context,150)));
            holder.cike_video.setVisibility(View.VISIBLE);
            x.image().bind(holder.cike_img,t.getPhoto().getThumbPhotoUrls().get(0),options);
            holder.cike_img.setAdjustViewBounds(true);
//            holder.cike_video.setVideoPath(t.getPhoto().getPhotoUrls().get(0));
//            holder.cike_video.start();
        }

//        holder.cike_img.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,t.getPhoto().getWidth()));
        x.image().bind(holder.cike_user_img,t.getCreateUserWrapper().getUser().getAvatarUrl());
        holder.cike_user_local.setText(t.getPhoto().getPlaceName());
        holder.cike_dianzan_count.setText(t.getPhoto().getLikeNum()+"");
        if (!t.getPhoto().getContent().equals("")) {
            holder.cike_content.setText(t.getPhoto().getContent());
        }else{
            holder.cike_content.setVisibility(View.GONE);
        }
        holder.cike_user_name.setText(t.getCreateUserWrapper().getUser().getNick());
        holder.photo_count.setText(t.getPhoto().getPhotoUrls().size()+"张");
    }

}
