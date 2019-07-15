package com.lingo.common_recyclerview.datasource;

import com.airbnb.epoxy.EpoxyModel;
import com.shizhefei.mvc.IDataSource;

import java.util.List;

public abstract class SyncDataSource implements IDataSource<List<EpoxyModel<?>>> {
    @Override
    public abstract List<EpoxyModel<?>> refresh() throws Exception;

    @Override
    public List<EpoxyModel<?>> loadMore() throws Exception {
        return null;
    }

    @Override
    public boolean hasMore() {
        return false;
    }
}
