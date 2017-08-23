package com.android.example.github.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.example.github.R;
import com.android.example.github.binding.FragmentDataBindingComponent;
import com.android.example.github.di.Injectable;
import com.jktaihe.library.utils.AutoClearedValue;
import com.jktaihe.library.utils.ClassUtils;
import javax.inject.Inject;

/**
 * Created by jktaihe on 7/6/17.
 * blog: blog.jktaihe.com
 */

public abstract class DataBindFragment<M extends ViewModel, T extends ViewDataBinding> extends LifecycleFragment implements Injectable{

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    protected AutoClearedValue<T> binding;
    protected M viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        T dataBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ClassUtils.getClass(getClass(),0));
    }

    @Override
    public int toolbarId() {
        return R.id.toolbar;
    }

    @Override
    public int titleViewId() {
        return R.id.tv_title;
    }
}
