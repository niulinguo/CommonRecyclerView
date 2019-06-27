package com.lingo.example.datasource;

import com.airbnb.epoxy.EpoxyModel;
import com.lingo.common_recyclerview.datasource.SyncDataSource;
import com.lingo.example.data.TestDataBean;
import com.lingo.example.models.TestModel_;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MockDataSource extends SyncDataSource {

    private int mPage;

    private List<EpoxyModel<?>> loadData(int page) {
        ArrayList<EpoxyModel<?>> epoxyModels = new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            TestDataBean dataBean = new TestDataBean();
            dataBean.text = String.format(Locale.getDefault(), "%d页,%d条", page, i);
            epoxyModels.add(new TestModel_().dataBean(dataBean));

        }

        return epoxyModels;
    }

    @Override
    public List<EpoxyModel<?>> refresh() throws Exception {
        List<EpoxyModel<?>> epoxyModels = loadData(1);
        mPage = 1;
        return epoxyModels;
    }

    @Override
    public List<EpoxyModel<?>> loadMore() throws Exception {
        List<EpoxyModel<?>> epoxyModels = loadData(mPage + 1);
        mPage = mPage + 1;
        return epoxyModels;
    }

    @Override
    public boolean hasMore() {
        return true;
    }
}
