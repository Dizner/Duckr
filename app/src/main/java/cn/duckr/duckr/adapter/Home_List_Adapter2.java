package cn.duckr.duckr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.duckr.duckr.LogUtils;
import cn.duckr.duckr.R;
import cn.duckr.duckr.bean.Home_YaoYue;
import cn.duckr.duckr.listener.OnClickGoContent;
import cn.duckr.duckr.listener.OnClickGoPhoto;
import cn.duckr.duckr.utils.DIYUtils;

/**
 * Created by Dizner on 2016/11/11.
 */

public class Home_List_Adapter2 extends BaseAdapter {
    private List<Home_YaoYue.DataBean.InviteWrapperListBean> list;
    private Context context;

    public Home_List_Adapter2(List<Home_YaoYue.DataBean.InviteWrapperListBean> list, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.home_yaoyue_content,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(holder,position);
        convertView.setContentDescription(list.get(position).getShareUrl());
        convertView.setOnClickListener(new OnClickGoContent(context));
        return convertView;
    }

    class ViewHolder{
        private ImageView yy_user_img,home_yy_content_img_1,home_yy_content_img_2,home_yy_content_img_3;
        private TextView home_yy_username,home_yy_user_type,home_yy_user_age,home_yy_user_money,home_yy_target,home_yy_title,home_yy_tdate,home_yy_content
                ,home_yy_datecount,home_yy_pinglun,home_yy_xingqu,home_yy_tag_1,home_yy_tag_2,home_yy_tag_3;
        public ViewHolder(View itemView) {
            yy_user_img= (ImageView) itemView.findViewById(R.id.yy_user_img);
            home_yy_content_img_1= (ImageView) itemView.findViewById(R.id.home_yy_content_img_1);
            home_yy_content_img_2= (ImageView) itemView.findViewById(R.id.home_yy_content_img_2);
            home_yy_content_img_3= (ImageView) itemView.findViewById(R.id.home_yy_content_img_3);
            home_yy_username= (TextView) itemView.findViewById(R.id.home_yy_username);
            home_yy_user_type= (TextView) itemView.findViewById(R.id.home_yy_user_type);
            home_yy_user_age= (TextView) itemView.findViewById(R.id.home_yy_user_age);
            home_yy_user_money= (TextView) itemView.findViewById(R.id.home_yy_user_money);
            home_yy_target= (TextView) itemView.findViewById(R.id.home_yy_target);
//            home_yy_title= (TextView) itemView.findViewById(R.id.home_yy_title);
            home_yy_tdate= (TextView) itemView.findViewById(R.id.home_yy_tdate);
            home_yy_content= (TextView) itemView.findViewById(R.id.home_yy_content);
            home_yy_datecount= (TextView) itemView.findViewById(R.id.home_yy_datecount);
            home_yy_pinglun= (TextView) itemView.findViewById(R.id.home_yy_pinglun);
            home_yy_xingqu= (TextView) itemView.findViewById(R.id.home_yy_xingqu);
            home_yy_tag_1= (TextView) itemView.findViewById(R.id.home_yy_tag_1);
            home_yy_tag_2= (TextView) itemView.findViewById(R.id.home_yy_tag_2);
            home_yy_tag_3= (TextView) itemView.findViewById(R.id.home_yy_tag_3);
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Home_YaoYue.DataBean.InviteWrapperListBean t = list.get(position);
        Home_YaoYue.DataBean.InviteWrapperListBean.InviteBean ac = t.getInvite();
        Home_YaoYue.DataBean.InviteWrapperListBean.CreateUserWrapperBean.UserBean user = t.getCreateUserWrapper().getUser();
        x.image().bind(holder.yy_user_img,user.getAvatarUrl());
        final ArrayList<String> photoUrls = (ArrayList) ac.getPhotoUrls();
        switch (photoUrls.size()) {
            case 0:
                break;
            case 1:
                x.image().bind(holder.home_yy_content_img_1,photoUrls.get(0));
                holder.home_yy_content_img_1.setOnClickListener(new OnClickGoPhoto(context,photoUrls,0));
                break;
            case 2:
                x.image().bind(holder.home_yy_content_img_1,photoUrls.get(0));
                x.image().bind(holder.home_yy_content_img_2,photoUrls.get(1));
                holder.home_yy_content_img_1.setOnClickListener(new OnClickGoPhoto(context,photoUrls,0));
                holder.home_yy_content_img_2.setOnClickListener(new OnClickGoPhoto(context,photoUrls,1));
                break;
            default:
                x.image().bind(holder.home_yy_content_img_1,photoUrls.get(0));
                x.image().bind(holder.home_yy_content_img_2,photoUrls.get(1));
                x.image().bind(holder.home_yy_content_img_3,photoUrls.get(2));
                holder.home_yy_content_img_1.setOnClickListener(new OnClickGoPhoto(context,photoUrls,0));
                holder.home_yy_content_img_2.setOnClickListener(new OnClickGoPhoto(context,photoUrls,1));
                holder.home_yy_content_img_3.setOnClickListener(new OnClickGoPhoto(context,photoUrls,2));
                break;
        }

        holder.home_yy_username.setText(user.getNick());
        String age = user.getAge() + "";
        if (age.length()>1) {
            holder.home_yy_user_age.setText(age.substring(age.length()-2));
        }
        holder.home_yy_user_age.setText(age);
        if (user.getGender()==1) {
            holder.home_yy_user_age.setBackgroundResource(R.drawable.boy);
        }else {
            holder.home_yy_user_age.setBackgroundResource(R.drawable.girl);
        }
        holder.home_yy_user_type.setText(ac.getThemeTitleMain());
        holder.home_yy_target.setText(ac.getDepartName()+" → "+ac.getDestNames());
        //格式化日期
        String time = ac.getStartTime();
        String week = DIYUtils.getWeekByDateStr(time.substring(0, 10));
        StringBuffer date=new StringBuffer(time.substring(5, 10).replaceFirst("\\-","月"))
                .append("日  "+week)
                .append(" ")
                .append("全程"+ac.getDaysLong()+"天");
//                .append(time.substring(11, 16)+"开始");
        holder.home_yy_tdate.setText(date.toString());
        holder.home_yy_content.setText(ac.getInviteBrief());
        LogUtils.out("内容",ac.getInviteBrief());
        if (!ac.getTagTitleMain().equals("")) {
            holder.home_yy_tag_1.setText(ac.getTagTitleMain());
            holder.home_yy_tag_1.setVisibility(View.VISIBLE);
        }
        if (!ac.getTagTitleSub().equals("")) {
            holder.home_yy_tag_2.setText(ac.getTagTitleSub());
            holder.home_yy_tag_2.setVisibility(View.VISIBLE);
        }
        switch (ac.getGenderLimit()){
            case 0:
                holder.home_yy_tag_3.setText("不限男女");
                break;
            case 1:
                holder.home_yy_tag_3.setText("仅限男生");
                break;
            case 2:
                holder.home_yy_tag_3.setText("仅限女生");
                break;
        }
        holder.home_yy_tag_3.setVisibility(View.VISIBLE);
        holder.home_yy_pinglun.setText(ac.getCommentNum()+"");
        holder.home_yy_xingqu.setText(ac.getInterestNum()+"");
        int stime = Integer.parseInt(ac.getCreateTime().substring(8, 10));
        int etime = Integer.parseInt(ac.getUpdateTime().substring(8,10));
        if (etime-stime<2) {
            holder.home_yy_datecount.setText("昨天 "+ac.getCreateTime().substring(11,16)+" | "+ac.getPublishPlace());
        }else if(etime-stime<4){
            holder.home_yy_datecount.setText(etime-stime+"天前 | "+ac.getPublishPlace());
        }else {
            holder.home_yy_datecount.setText(ac.getCreateTime().substring(5,10)+" | "+ac.getPublishPlace());
        }


    }

}
