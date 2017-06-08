package com.android.example.github.ui;

import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.android.example.github.binding.FragmentDataBindingComponent;
import com.android.example.github.di.Injectable;
import com.android.example.github.util.AutoClearedValue;

import javax.inject.Inject;

/**
 * Created by jktaihe on 7/6/17.
 * blog: blog.jktaihe.com
 */

public abstract class BaseFragment< T extends ViewDataBinding> extends LifecycleFragment implements Injectable, IBaseView {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    protected Activity activity;

    public DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    public AutoClearedValue<T> binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        T dataBinding = DataBindingUtil
                .inflate(inflater, layoutId(), container, false,
                        dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        initView(savedInstanceState);

        return dataBinding.getRoot();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            activity = (Activity) context;
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    protected void dismissKeyboard(IBinder windowToken) {
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(windowToken, 0);
        }
    }

}
