package com.lingo.common_recyclerview;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    protected View mRootView;

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = onCreateRootView(inflater, container, savedInstanceState);

        initRootView(mRootView, savedInstanceState);

        return mRootView;
    }

    public abstract View onCreateRootView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    public abstract void initRootView(View rootView, @Nullable Bundle savedInstanceState);

    public final <T extends View> T findViewById(@IdRes int id) {
        if (mRootView == null) {
            return null;
        }
        return mRootView.findViewById(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mRootView = null;
    }
}
