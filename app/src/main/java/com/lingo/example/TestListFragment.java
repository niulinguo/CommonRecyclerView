package com.lingo.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.airbnb.epoxy.EpoxyModel;
import com.lingo.common_recyclerview.epoxy.fragment.CoolRefreshListFragment;
import com.lingo.example.datasource.AsyncMockDataSource;
import com.shizhefei.mvc.ILoadViewFactory;
import com.shizhefei.mvc.MVCHelper;

import java.util.List;

public class TestListFragment extends CoolRefreshListFragment {

    @Override
    protected void initRefreshHelper(MVCHelper<List<EpoxyModel<?>>> refreshHelper, @Nullable Bundle savedInstanceState) {
        super.initRefreshHelper(refreshHelper, savedInstanceState);

        refreshHelper.setDataSource(new AsyncMockDataSource());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getRefreshHelper().refresh();
    }

    @Override
    protected ILoadViewFactory.ILoadMoreView createLoadMoreView() {
        return null;
    }
}
