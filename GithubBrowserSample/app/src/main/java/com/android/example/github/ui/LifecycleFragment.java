package com.android.example.github.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;

import com.jktaihe.library.view.TitleFragment;

/**
 * Created by jktaihe on 7/6/17.
 * blog: blog.jktaihe.com
 */

public abstract class LifecycleFragment extends TitleFragment implements LifecycleRegistryOwner {

    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    @Override
    public LifecycleRegistry getLifecycle() {
        return this.mLifecycleRegistry;
    }

}
