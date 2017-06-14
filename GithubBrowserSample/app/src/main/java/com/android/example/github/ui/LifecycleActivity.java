package com.android.example.github.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;

import com.android.example.github.binding.ActivityDataBindingComponent;
import com.android.example.github.util.AutoClearedValue;
import com.jktaihe.library.ui.BaseActivity;

import javax.inject.Inject;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by jktaihe on 7/6/17.
 * blog: blog.jktaihe.com
 */

public abstract class LifecycleActivity<M extends ViewModel, T extends ViewDataBinding> extends BaseActivity implements LifecycleRegistryOwner,HasSupportFragmentInjector{

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    public AutoClearedValue<T> binding;
    public M viewModel;
    public DataBindingComponent dataBindingComponent = new ActivityDataBindingComponent(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        T dataBinding = DataBindingUtil.setContentView(this,layoutId(),dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


}
