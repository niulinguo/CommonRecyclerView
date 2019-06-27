package com.lingo.example.models;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.lingo.example.R;
import com.lingo.example.data.TestDataBean;

@EpoxyModelClass(layout = R.layout.item_test_layout)
public abstract class TestModel extends EpoxyModelWithHolder<TestModel.ViewHolder> {

    @EpoxyAttribute
    TestDataBean dataBean;

    @Override
    public void bind(@NonNull ViewHolder holder) {
        super.bind(holder);

        holder.mTextView.setText(dataBean.text);
    }

    public static final class ViewHolder extends EpoxyHolder {

        TextView mTextView;

        @Override
        protected void bindView(@NonNull View itemView) {
            mTextView = itemView.findViewById(R.id.tv_text);
        }
    }

}
