package com.lingo.common_recyclerview.common;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.lingo.common_recyclerview.util.Utils;
import com.shizhefei.mvc.ILoadViewFactory;

public class LoadMoreHelper implements ILoadViewFactory.ILoadMoreView {

    protected TextView footView;

    protected View.OnClickListener onClickRefreshListener;

    @Override
    public void init(ILoadViewFactory.FootViewAdder footViewHolder, View.OnClickListener onClickRefreshListener) {
        View contentView = footViewHolder.getContentView();

        Context context = contentView.getContext();
        TextView textView = new TextView(context);
        textView.setTextColor(Color.GRAY);
        textView.setPadding(0, Utils.dip2px(context, 16), 0, Utils.dip2px(context, 16));
        textView.setGravity(Gravity.CENTER);
        footViewHolder.addFootView(textView);

        footView = textView;
        this.onClickRefreshListener = onClickRefreshListener;
        showNormal();
    }

    @Override
    public void showNormal() {
        footView.setText("点击加载更多");
        footView.setOnClickListener(onClickRefreshListener);
    }

    @Override
    public void showLoading() {
        footView.setText("正在加载中..");
        footView.setOnClickListener(null);
    }

    @Override
    public void showFail(Exception exception) {
        footView.setText("加载失败，点击重新加载");
        footView.setOnClickListener(onClickRefreshListener);
    }

    @Override
    public void showNomore() {
        footView.setText("已经加载完毕");
        footView.setOnClickListener(null);
    }

}
