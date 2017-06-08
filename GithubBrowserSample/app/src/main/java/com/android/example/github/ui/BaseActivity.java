package com.android.example.github.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Inject;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by jktaihe on 7/6/17.
 * blog: blog.jktaihe.com
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView ,LifecycleRegistryOwner,HasSupportFragmentInjector{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        initView(savedInstanceState);
    }


    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
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

    protected void dismissKeyboard(IBinder windowToken) {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(windowToken, 0);
    }
}
