package com.android.example.github.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by jktaihe on 7/6/17.
 * blog: blog.jktaihe.com
 */

public interface IBaseView {
    void initView(@Nullable Bundle savedInstanceState);
    int resID();
}
