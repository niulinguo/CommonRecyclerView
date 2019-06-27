package com.lingo.example.datasource;

import android.os.Handler;

import com.airbnb.epoxy.EpoxyModel;
import com.lingo.example.data.TestDataBean;
import com.lingo.common_recyclerview.datasource.AsyncDataSource;
import com.lingo.example.models.TestModel_;
import com.shizhefei.mvc.RequestHandle;
import com.shizhefei.mvc.ResponseSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AsyncMockDataSource extends AsyncDataSource {

    private int mPage;

    private RequestHandle loadData(final int page, final ResponseSender<List<EpoxyModel<?>>> sender) {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<EpoxyModel<?>> epoxyModels = new ArrayList<>();

                for (int i = 0; i < 20; i++) {

                    TestDataBean dataBean = new TestDataBean();
                    dataBean.text = String.format(Locale.getDefault(), "%d页,%d条", page, i);
                    epoxyModels.add(new TestModel_().dataBean(dataBean));

                }

                sender.sendData(epoxyModels);
            }
        }, 3000);

        return new RequestHandle() {
            @Override
            public void cancle() {
                handler.removeCallbacksAndMessages(null);
            }

            @Override
            public boolean isRunning() {
                return false;
            }
        };
    }

    @Override
    public RequestHandle refresh(final ResponseSender<List<EpoxyModel<?>>> sender) throws Exception {
        return loadData(1, new ResponseSender<List<EpoxyModel<?>>>() {
            @Override
            public void sendError(Exception exception) {
                sender.sendError(exception);
            }

            @Override
            public void sendData(List<EpoxyModel<?>> epoxyModels) {
                mPage = 1;
                sender.sendData(epoxyModels);
            }

            @Override
            public void sendProgress(long current, long total, Object extraData) {
                sender.sendProgress(current, total, extraData);
            }
        });
    }

    @Override
    public RequestHandle loadMore(final ResponseSender<List<EpoxyModel<?>>> sender) throws Exception {
        return loadData(mPage + 1, new ResponseSender<List<EpoxyModel<?>>>() {
            @Override
            public void sendError(Exception exception) {
                sender.sendError(exception);
            }

            @Override
            public void sendData(List<EpoxyModel<?>> epoxyModels) {
                mPage = mPage + 1;
                sender.sendData(epoxyModels);
            }

            @Override
            public void sendProgress(long current, long total, Object extraData) {
                sender.sendProgress(current, total, extraData);
            }
        });
    }

    @Override
    public boolean hasMore() {
        return true;
    }
}
