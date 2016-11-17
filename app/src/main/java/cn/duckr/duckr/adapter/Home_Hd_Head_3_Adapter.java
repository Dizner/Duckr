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

/**
 * Created by Dizner on 2016/11/9.
 */

public class Home_Hd_Head_3_Adapter extends RecyclerView.Adapter<Home_Hd_Head_3_Adapter.RecHolder> {
    private List<Home_HuoDong.DataBean.HomeRcmdWrapperListBean.ThemeListBean> list;
    private Context context;

    public Home_Hd_Head_3_Adapter(List<Home_HuoDong.DataBean.HomeRcmdWrapperListBean.ThemeListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.home_hd_head_3_content,parent,false);
        return new RecHolder(v);
    }

    @Override
    public void onBindViewHolder(RecHolder holder, int position) {
        Home_HuoDong.DataBean.HomeRcmdWrapperListBean.ThemeListBean t = list.get(position+1);

        x.image().bind(holder.home_hd_head_img,t.getCoverUrl());


        holder.home_hd_head_title.setText(t.getTitle());
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
            home_hd_head_img = (ImageView) itemView.findViewById(R.id.home_ha_head_3_img);
            home_hd_head_title = (TextView) itemView.findViewById(R.id.home_ha_head_3_title);
        }
    }
}
