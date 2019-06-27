package com.lingo.common_recyclerview.epoxy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.epoxy.EpoxyModel;
import com.lingo.common_recyclerview.R;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.mvc.MVCHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;

import java.util.List;

public class CoolRefreshListFragment extends CommonListFragment {
    @Override
    protected RecyclerView findRecyclerView(View rootView, @Nullable Bundle savedInstanceState) {
        return findViewById(R.id.rv_list);
    }

    @Override
    protected MVCHelper<List<EpoxyModel<?>>> createMVCHelper(View rootView, @Nullable Bundle savedInstanceState) {
        MVCCoolHelper<List<EpoxyModel<?>>> refreshHelper = new MVCCoolHelper<>(findCoolRefreshView(), createLoadView(), createLoadMoreView());

        initRefreshHelper(refreshHelper);

        return refreshHelper;
    }

    protected void initRefreshHelper(MVCHelper<List<EpoxyModel<?>>> refreshHelper) {
        refreshHelper.setNeedCheckNetwork(false);
    }

    protected CoolRefreshView findCoolRefreshView() {
        return findViewById(R.id.cool_refresh_view);
    }

    @Override
    public View onCreateRootView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_common_cool_refresh_list, container, false);
    }
}
