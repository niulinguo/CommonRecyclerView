package com.lingo.common_recyclerview.datasource;

import com.airbnb.epoxy.EpoxyModel;
import com.shizhefei.mvc.IAsyncDataSource;
import com.shizhefei.mvc.RequestHandle;
import com.shizhefei.mvc.ResponseSender;

import java.util.List;

public abstract class AsyncDataSource implements IAsyncDataSource<List<EpoxyModel<?>>> {
    @Override
    public abstract RequestHandle refresh(ResponseSender<List<EpoxyModel<?>>> sender) throws Exception;

    @Override
    public RequestHandle loadMore(ResponseSender<List<EpoxyModel<?>>> sender) throws Exception {
        return null;
    }

    @Override
    public boolean hasMore() {
        return false;
    }
}
