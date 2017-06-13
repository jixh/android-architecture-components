package com.android.example.github.ui;

import android.app.Activity;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.example.github.binding.FragmentDataBindingComponent;
import com.android.example.github.di.Injectable;
import com.android.example.github.util.AutoClearedValue;
import com.jktaihe.library.ui.BaseFragment;
import com.jktaihe.library.utils.ClassUtils;
import javax.inject.Inject;

/**
 * Created by jktaihe on 7/6/17.
 * blog: blog.jktaihe.com
 */

public abstract class LifecycleFragment<M extends ViewModel, T extends ViewDataBinding> extends BaseFragment implements Injectable, LifecycleRegistryOwner {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    protected AppCompatActivity activity;

    public DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    public AutoClearedValue<T> binding;
    public M viewModel;

    LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    public LifecycleRegistry getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        T dataBinding = DataBindingUtil
                .inflate(inflater, layoutId(), container, false,
                        dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ClassUtils.getClass(getClass()));
        initView(savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            activity = (AppCompatActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }



}
