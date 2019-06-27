package com.lingo.common_recyclerview;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lingo.common_recyclerview.util.Utils;
import com.shizhefei.mvc.ILoadViewFactory;
import com.shizhefei.view.vary.VaryViewHelper;

public class LoadViewHelper implements ILoadViewFactory.ILoadView {

    private VaryViewHelper helper;
    private View.OnClickListener onClickRefreshListener;
    private Context context;

    @Override
    public void init(View switchView, View.OnClickListener onClickRefreshListener) {
        this.context = switchView.getContext().getApplicationContext();
        this.onClickRefreshListener = onClickRefreshListener;
        helper = new VaryViewHelper(switchView);
    }

    @Override
    public void restore() {
        helper.restoreView();
    }

    @Override
    public void showLoading() {
        Context context = helper.getContext();

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);

        ProgressBar progressBar = new ProgressBar(context);
        layout.addView(progressBar);

        TextView textView = new TextView(context);
        textView.setText("加载中...");
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int top = Utils.dip2px(context, 12);
        params.setMargins(0, top, 0, 0);
        layout.addView(textView, params);

        helper.showLayout(layout);
    }

    @Override
    public void tipFail(Exception exception) {
        Toast.makeText(context, "网络加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFail(Exception exception) {
        Context context = helper.getContext();

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);

        TextView textView = new TextView(context);
        textView.setText("网络加载失败");
        textView.setGravity(Gravity.CENTER);
        layout.addView(textView);

        Button button = new Button(context);
        button.setText("重试");
        button.setOnClickListener(onClickRefreshListener);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int top = Utils.dip2px(context, 12);
        params.setMargins(0, top, 0, 0);
        layout.addView(button, params);

        helper.showLayout(layout);
    }

    @Override
    public void showEmpty() {
        Context context = helper.getContext();

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);

        TextView textView = new TextView(context);
        textView.setText("暂无数据");
        textView.setGravity(Gravity.CENTER);
        layout.addView(textView);

        Button button = new Button(context);
        button.setText("重试");
        button.setOnClickListener(onClickRefreshListener);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int top = Utils.dip2px(context, 12);
        params.setMargins(0, top, 0, 0);
        layout.addView(button, params);

        helper.showLayout(layout);
    }

}
