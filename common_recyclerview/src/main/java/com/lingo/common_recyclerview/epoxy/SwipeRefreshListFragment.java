package com.lingo.common_recyclerview.epoxy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.epoxy.EpoxyModel;
import com.lingo.common_recyclerview.R;
import com.shizhefei.mvc.MVCHelper;
import com.shizhefei.mvc.MVCSwipeRefreshHelper;

import java.util.List;

public class SwipeRefreshListFragment extends CommonListFragment {

    @Override
    protected RecyclerView findRecyclerView(View rootView, @Nullable Bundle savedInstanceState) {
        return findViewById(R.id.rv_list);
    }

    @Override
    protected MVCHelper<List<EpoxyModel<?>>> createMVCHelper(View rootView, @Nullable Bundle savedInstanceState) {
        MVCSwipeRefreshHelper<List<EpoxyModel<?>>> refreshHelper = new MVCSwipeRefreshHelper<>(findSwipeRefreshLayout(), createLoadView(), createLoadMoreView());

        initRefreshHelper(refreshHelper);

        return refreshHelper;
    }

    protected void initRefreshHelper(MVCHelper<List<EpoxyModel<?>>> refreshHelper) {
        refreshHelper.setNeedCheckNetwork(false);
    }

    protected SwipeRefreshLayout findSwipeRefreshLayout() {
        return findViewById(R.id.swipe_refresh_layout);
    }

    @Override
    public View onCreateRootView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_common_swipe_refresh_list, container, false);
    }
}
