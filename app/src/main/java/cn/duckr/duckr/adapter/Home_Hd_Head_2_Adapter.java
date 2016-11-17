package cn.duckr.duckr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import cn.duckr.duckr.R;
import cn.duckr.duckr.bean.Home_HuoDong;
import cn.duckr.duckr.listener.OnClickGoWeb;

/**
 * Created by Dizner on 2016/11/9.
 */

public class Home_Hd_Head_2_Adapter extends RecyclerView.Adapter<Home_Hd_Head_2_Adapter.RecHolder> {
    private List<Home_HuoDong.DataBean.HomeRcmdWrapperListBean.ActivWrapperListBean> list;
    private Context context;

    public Home_Hd_Head_2_Adapter(List<Home_HuoDong.DataBean.HomeRcmdWrapperListBean.ActivWrapperListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.home_hd_head_2_content,parent,false);
        return new RecHolder(v);
    }

    @Override
    public void onBindViewHolder(RecHolder holder, int position) {
        Home_HuoDong.DataBean.HomeRcmdWrapperListBean.ActivWrapperListBean t = list.get(position+1);
        Home_HuoDong.DataBean.HomeRcmdWrapperListBean.ActivWrapperListBean.ActivBean ac = t.getActiv();
        x.image().bind(holder.home_hd_head_img,ac.getPhotoUrls().get(0));
        //设置条目点击事件
        holder.itemView.setContentDescription(t.getShareUrl());
        holder.itemView.setOnClickListener(new OnClickGoWeb(context));

        holder.home_hd_head_title.setText(ac.getTitle());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size()-1;
    }

    static class RecHolder extends RecyclerView.ViewHolder{
        private ImageView home_hd_head_img;
        private TextView home_hd_head_title;
        public RecHolder(View itemView) {
            super(itemView);
            home_hd_head_img = (ImageView) itemView.findViewById(R.id.home_ha_head_2_img);
            home_hd_head_title = (TextView) itemView.findViewById(R.id.home_ha_head_2_title);
        }
    }
}
