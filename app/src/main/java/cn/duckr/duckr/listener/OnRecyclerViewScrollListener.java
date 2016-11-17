package cn.duckr.duckr.listener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by Dizner on 2016/11/11.
 */

public abstract class OnRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private String TAG = getClass().getSimpleName();

    public static enum LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED_GRID
    }

    /**
     * layoutManager的类型（枚举）
     */
    protected LAYOUT_MANAGER_TYPE layoutManagerType;

    /**
     * 最后一个的位置
     */
    private int[] lastPositions;

    /**
     * 最后一个可见的item的位置
     */
    private int lastVisibleItemPosition;
/*    *//**
     * 是否正在加载
     *//*
    private boolean isLoadingMore = false;*/

    /**
     * 当前滑动的状态
     */
    private int currentScrollState = 0;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //  int lastVisibleItemPosition = -1;
        //判断布局管理器的类型
        if (layoutManagerType == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.LINEAR;
            } else if (layoutManager instanceof GridLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.GRID;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.STAGGERED_GRID;
            } else {
                throw new RuntimeException(
                        "不支持该类型的布局管理器. 只支持 LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager类型");
            }
        }
        //调用布局管理器的findLastVisibleItemPosition()方法来寻找最后一条可见的条目,并拿到位置.
        switch (layoutManagerType) {
            case LINEAR:
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case GRID:
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
//            case STAGGERED_GRID:
//                StaggeredGridLayoutManager staggeredGridLayoutManager
//                        = (StaggeredGridLayoutManager) layoutManager;
//                if (lastPositions == null) {
//                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
//                }
//                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
//                lastVisibleItemPosition = findMax(lastPositions);
//                break;
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        currentScrollState = newState;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //得到可见条目的计数
        int visibleItemCount = layoutManager.getChildCount();
        //得到全部条目的条数
        int totalItemCount = layoutManager.getItemCount();
        //判断，如果可见条目大于0，且滑动状态为闲置状态，最后可见条目的位置大于等于总条目数减一（最后一个条目可见状态），即到达底部。
        if ((visibleItemCount > 0 && currentScrollState == RecyclerView.SCROLL_STATE_IDLE &&
                (lastVisibleItemPosition) >= totalItemCount - 1)) {
            //Log.d(TAG, "is loading more");
            onBottom();
        }
    }

    public abstract void onBottom();

//    private int findMax(int[] lastPositions) {
//        int max = lastPositions[0];
//        for (int value : lastPositions) {
//            if (value > max) {
//                max = value;
//            }
//        }
//        return max;
//    }
}
