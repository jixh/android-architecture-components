package com.jktaihe.library.view;

import android.support.annotation.IdRes;

/**
 * Created by hzjixiaohui on 2017-7-25.
 */

public interface ITitleView {
    void initView();
    @IdRes int toolbarId();
    @IdRes int titleViewId();
}
