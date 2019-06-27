package com.lingo.common_recyclerview.epoxy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.airbnb.epoxy.EpoxyViewHolder;
import com.shizhefei.recyclerview.HFAdapter;

@SuppressWarnings("unchecked")
public class FixEpoxyAdapter extends HFAdapter {

    protected RecyclerView.Adapter adapter;

    public FixEpoxyAdapter(RecyclerView.Adapter adapter) {
        this(adapter, true);
    }

    public FixEpoxyAdapter(RecyclerView.Adapter adapter, boolean needSetClickListener) {
        super(needSetClickListener);
        this.adapter = adapter;
        adapter.registerAdapterDataObserver(adapterDataObserver);
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    private RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() {

        @Override
        public void onChanged() {
            FixEpoxyAdapter.this.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            FixEpoxyAdapter.this.notifyItemRangeChanged(positionStart + getHeadSize(), itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            FixEpoxyAdapter.this.notifyItemRangeInserted(positionStart + getHeadSize(), itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            FixEpoxyAdapter.this.notifyItemRangeRemoved(positionStart + getHeadSize(), itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            FixEpoxyAdapter.this.notifyItemMoved(fromPosition + getHeadSize(), toPosition + getHeadSize());
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            FixEpoxyAdapter.this.notifyItemRangeChanged(positionStart + getHeadSize(), itemCount, payload);
        }
    };

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderHF(ViewGroup viewGroup, int type) {
        return adapter.onCreateViewHolder(viewGroup, type);
    }

    @Override
    public void onBindViewHolderHF(RecyclerView.ViewHolder vh, int position) {
        adapter.onBindViewHolder(vh, position);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        adapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        adapter.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        if (holder instanceof EpoxyViewHolder) {
            adapter.onViewAttachedToWindow(holder);
        } else {
            super.onViewAttachedToWindow(holder);
        }
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder holder) {
        if (holder instanceof EpoxyViewHolder) {
            return adapter.onFailedToRecycleView(holder);
        } else {
            return super.onFailedToRecycleView(holder);
        }
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        if (holder instanceof EpoxyViewHolder) {
            adapter.onViewRecycled(holder);
        } else {
            super.onViewRecycled(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        if (holder instanceof EpoxyViewHolder) {
            adapter.onViewDetachedFromWindow(holder);
        } else {
            super.onViewDetachedFromWindow(holder);
        }
    }

    @Override
    public int getItemCountHF() {
        return adapter.getItemCount();
    }

    @Override
    public int getItemViewTypeHF(int position) {
        return adapter.getItemViewType(position);
    }

    @Override
    public long getItemIdHF(int position) {
        return adapter.getItemId(position);
    }

}
