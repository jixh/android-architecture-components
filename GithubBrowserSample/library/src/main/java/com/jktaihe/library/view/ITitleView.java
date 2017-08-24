package com.jktaihe.library.view;

import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;

/**
 * Created by hzjixiaohui on 2017-7-25.
 */

public interface ITitleView {
    void initView();
    @IdRes int toolbarId();
    @IdRes int titleViewId();
    void setTitle(@NonNull String title);
    void setTitle(@StringRes int titleId);
    void setBack();
//    void hideBack();
    void showRightMenu(@MenuRes int resId, Toolbar.OnMenuItemClickListener onMenuItemClickListener);
    void showRightMenu(@Nullable String txt, @Nullable int imageId, Toolbar.OnMenuItemClickListener onMenuItemClickListener);
    void hideRightMenu();
}
