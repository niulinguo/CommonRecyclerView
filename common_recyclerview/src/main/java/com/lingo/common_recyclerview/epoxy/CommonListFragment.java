package com.lingo.common_recyclerview.epoxy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.airbnb.epoxy.EpoxyModel;
import com.lingo.common_recyclerview.BaseFragment;
import com.lingo.common_recyclerview.LoadMoreHelper;
import com.lingo.common_recyclerview.LoadViewHelper;
import com.shizhefei.mvc.ILoadViewFactory;
import com.shizhefei.mvc.MVCHelper;

import java.util.List;

public abstract class CommonListFragment extends BaseFragment {

    protected MVCHelper<List<EpoxyModel<?>>> mRefreshHelper;

    @Override
    public final void initRootView(View rootView, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = findRecyclerView(rootView, savedInstanceState);
        CommonListAdapter adapter = createAdapter(rootView, savedInstanceState);
        recyclerView.setAdapter(adapter);

        mRefreshHelper = createMVCHelper(rootView, savedInstanceState);
        ILoadViewFactory.ILoadMoreView loadMoreView = mRefreshHelper.getLoadMoreView();
        if (loadMoreView != null) {
            mRefreshHelper.setAdapter2(new FixEpoxyAdapter(adapter), adapter);
        } else {
            mRefreshHelper.setAdapter2(adapter, adapter);
        }

    }

    protected abstract RecyclerView findRecyclerView(View rootView, @Nullable Bundle savedInstanceState);

    protected CommonListAdapter createAdapter(View rootView, @Nullable Bundle savedInstanceState) {
        return new CommonListAdapter(false);
    }

    protected abstract MVCHelper<List<EpoxyModel<?>>> createMVCHelper(View rootView, @Nullable Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mRefreshHelper != null) {
            mRefreshHelper.destory();
            mRefreshHelper = null;
        }
    }

    public MVCHelper getRefreshHelper() {
        return mRefreshHelper;
    }

    protected ILoadViewFactory.ILoadView createLoadView() {
        return new LoadViewHelper();
    }

    protected ILoadViewFactory.ILoadMoreView createLoadMoreView() {
        return new LoadMoreHelper();
    }
}
