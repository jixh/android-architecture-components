package com.android.example.github.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.jktaihe.library.utils.ClassUtils;
import com.jktaihe.library.view.LifecycleActivity;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jktaihe on 7/6/17.
 * blog: blog.jktaihe.com
 */

public abstract class DataBindActivity<M extends ViewModel, T extends ViewDataBinding> extends LifecycleActivity implements HasSupportFragmentInjector {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public T binding;
    public M viewModel;
    private final CompositeDisposable mDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,layoutId());
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ClassUtils.getClass(getClass(),0));
    }

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mDisposable.clear();
    }
}
