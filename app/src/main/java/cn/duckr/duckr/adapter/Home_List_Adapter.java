package cn.duckr.duckr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.Date;
import java.util.List;

import cn.duckr.duckr.DrukApp;
import cn.duckr.duckr.LogUtils;
import cn.duckr.duckr.R;
import cn.duckr.duckr.bean.Home_HuoDong;
import cn.duckr.duckr.bean.Shoucang;
import cn.duckr.duckr.listener.OnClickGoWeb;

/**
 * Created by Dizner on 2016/11/11.
 */

public class Home_List_Adapter extends BaseAdapter {
    private List<Home_HuoDong.DataBean.HotActivWrapperListBean> list;
    private Context context;
    private DbManager db;
    public Home_List_Adapter(List<Home_HuoDong.DataBean.HotActivWrapperListBean> list, Context context) {
        this.list = list;
        this.context = context;
        db=x.getDb(new DbManager.DaoConfig());
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
            convertView= LayoutInflater.from(context).inflate(R.layout.home_huodong_content,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(holder,position);
        final Home_HuoDong.DataBean.HotActivWrapperListBean listBean = list.get(position);
        try {
            Shoucang id = db.selector(Shoucang.class).where(WhereBuilder.b("_id", "=", listBean.getShareUrl())).findFirst();
            if (id!=null) {
                holder.home_hd_xingqu.setChecked(true);
            }

        } catch (DbException e) {
            LogUtils.out("收藏","初始化异常");
        }
        holder.home_hd_xingqu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String s = new Date().toString();

                if (isChecked) {
                    try {
                        db.saveOrUpdate(new Shoucang(DrukApp.getUserID(),listBean.getShareTitle(),1,listBean.getShareUrl(),s));
                        Toast.makeText(context,"收藏成功",Toast.LENGTH_SHORT).show();
                    } catch (DbException e) {
                        Toast.makeText(context,"收藏失败",Toast.LENGTH_SHORT).show();
                        buttonView.setChecked(false);
                    }
                }else{
                    try {
                        db.delete(Shoucang.class, WhereBuilder.b("_id","=",listBean.getShareUrl()));
                        Toast.makeText(context,"取消成功",Toast.LENGTH_SHORT).show();
                    } catch (DbException e) {
                        Toast.makeText(context,"取消失败",Toast.LENGTH_SHORT).show();
                        buttonView.setChecked(true);
                    }
                }
            }
        });
        convertView.setContentDescription(list.get(position).getShareUrl());
        convertView.setOnClickListener(new OnClickGoWeb(context));
        return convertView;
    }

    class ViewHolder{
        private ImageView hd_list_img,home_hd_userimg_1,home_hd_userimg_2,home_hd_userimg_3;
        private TextView hd_title,hd_addr,hd_time,hd_type,hd_pice,hd_picetype,home_hd_looks;
        private CheckBox home_hd_xingqu;
        public ViewHolder(View itemView) {
            hd_list_img = (ImageView) itemView.findViewById(R.id.hd_list_img);
            home_hd_userimg_1= (ImageView) itemView.findViewById(R.id.home_hd_userimg_1);
            home_hd_userimg_2= (ImageView) itemView.findViewById(R.id.home_hd_userimg_2);
            home_hd_userimg_3= (ImageView) itemView.findViewById(R.id.home_hd_userimg_3);
            home_hd_userimg_1.setVisibility(View.GONE);
            home_hd_userimg_2.setVisibility(View.GONE);
            home_hd_userimg_3.setVisibility(View.GONE);
            hd_title = (TextView) itemView.findViewById(R.id.hd_title);
            hd_addr = (TextView) itemView.findViewById(R.id.hd_addr);
            hd_time = (TextView) itemView.findViewById(R.id.hd_time);
            hd_type = (TextView) itemView.findViewById(R.id.hd_type);
            hd_pice = (TextView) itemView.findViewById(R.id.hd_picel);
            hd_picetype = (TextView) itemView.findViewById(R.id.hd_piceltype);
            home_hd_looks= (TextView) itemView.findViewById(R.id.home_hd_looks);
            home_hd_xingqu= (CheckBox) itemView.findViewById(R.id.home_hd_xingqu);
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Home_HuoDong.DataBean.HotActivWrapperListBean t = list.get(position);
        Home_HuoDong.DataBean.HotActivWrapperListBean.ActivBeanX ac = t.getActiv();
        List<Home_HuoDong.DataBean.HotActivWrapperListBean.DisplayUserListBeanX> userList = t.getDisplayUserList();
        x.image().bind(holder.hd_list_img,ac.getPhotoUrls().get(0));
        switch (userList.size()) {
            case 0:
                break;
            case 1:
                x.image().bind(holder.home_hd_userimg_1,userList.get(0).getThumbAvatarUrl());
                holder.home_hd_userimg_1.setVisibility(View.VISIBLE);
                break;
            case 2:
                x.image().bind(holder.home_hd_userimg_1,userList.get(0).getThumbAvatarUrl());
                holder.home_hd_userimg_1.setVisibility(View.VISIBLE);
                x.image().bind(holder.home_hd_userimg_2,userList.get(1).getThumbAvatarUrl());
                holder.home_hd_userimg_2.setVisibility(View.VISIBLE);
                break;
            default:
                x.image().bind(holder.home_hd_userimg_1,userList.get(0).getThumbAvatarUrl());
                holder.home_hd_userimg_1.setVisibility(View.VISIBLE);
                x.image().bind(holder.home_hd_userimg_2,userList.get(1).getThumbAvatarUrl());
                holder.home_hd_userimg_2.setVisibility(View.VISIBLE);
                x.image().bind(holder.home_hd_userimg_3,userList.get(2).getThumbAvatarUrl());
                holder.home_hd_userimg_3.setVisibility(View.VISIBLE);
                break;
        }

        holder.hd_title.setText(ac.getTitle());
        holder.hd_addr.setText(ac.getGatherAddr());
        holder.hd_time.setText(t.getCellTime());
        holder.hd_type.setText(ac.getThemeTitleMain());
        holder.hd_pice.setText(t.getPriceText());
        int type = t.getPriceType();
        holder.hd_picetype.setText(type==1?"起/人":"/人");
        holder.home_hd_looks.setText(t.getDisplayText());


    }

}
