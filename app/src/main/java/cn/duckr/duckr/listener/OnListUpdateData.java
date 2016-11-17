package cn.duckr.duckr.listener;

import android.app.Activity;
import android.view.View;
import android.widget.AbsListView;

/**
 * Created by Dizner on 2016/10/20.
 */
public abstract class OnListUpdateData implements AbsListView.OnScrollListener {
    private Activity activity=null;
    private int getLastVisiblePosition = 0,lastVisiblePositionY=0;
    public OnListUpdateData(Activity activity) {
        this.activity=activity;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            //滚动到底部
            if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                View v=(View) view.getChildAt(view.getChildCount()-1);
                int[] location = new  int[2] ;
                v.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
                int y=location [1];
                if (view.getLastVisiblePosition()!=getLastVisiblePosition
                        && lastVisiblePositionY!=y)//第一次拖至底部
                {
//                    Toast.makeText(activity, "再次拖至底部，即可翻页",Toast.LENGTH_SHORT).show();
                    getLastVisiblePosition=view.getLastVisiblePosition();
                    lastVisiblePositionY=y;
                    return;
                }
                else if (view.getLastVisiblePosition()==getLastVisiblePosition
                        && lastVisiblePositionY==y)//第二次拖至底部
                {
//                    mCallback.execute(">>>>>拖至底部");
                    upDate();
//                    Toast.makeText(activity, "正在刷新",Toast.LENGTH_SHORT).show();
                }
            }
            //未滚动到底部，第二次拖至底部都初始化
            getLastVisiblePosition=0;
            lastVisiblePositionY=0;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        if(totalItemCount!=0&&totalItemCount == firstVisibleItem + visibleItemCount){
//
//        }
    }
    public abstract void upDate();
}
