package com.jktaihe.library.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

/**
 * Created by jktaihe on 13/6/17.
 * blog: blog.jktaihe.com
 */

public interface IView {

    void initView(@Nullable Bundle savedInstanceState);

    @LayoutRes int layoutId();

}
