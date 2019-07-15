package com.lingo.common_recyclerview.epoxy.adapter;

import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.SimpleEpoxyAdapter;
import com.shizhefei.mvc.IDataAdapter;

import java.util.List;

public class CommonListAdapter extends SimpleEpoxyAdapter implements IDataAdapter<List<EpoxyModel<?>>> {

    private final boolean mEnableDiff;

    public CommonListAdapter(boolean enableDiff) {
        mEnableDiff = enableDiff;

        if (mEnableDiff) {
            enableDiffing();
        }
    }

    public CommonListAdapter() {
        this(false);
    }

    @Override
    public void notifyDataChanged(List<EpoxyModel<?>> epoxyModels, boolean isRefresh) {
        if (isRefresh) {
            getModels().clear();
        }

        if (epoxyModels != null && !epoxyModels.isEmpty()) {
            getModels().addAll(epoxyModels);
        }

        if (mEnableDiff) {
            notifyModelsChanged();
        } else {
            notifyDataSetChanged();
        }
    }

    @Override
    public List<EpoxyModel<?>> getData() {
        return getModels();
    }
}
