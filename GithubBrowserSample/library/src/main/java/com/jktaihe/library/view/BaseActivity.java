package com.jktaihe.library.view;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * Created by jktaihe on 13/6/17.
 * blog: blog.jktaihe.com
 */

public abstract class BaseActivity extends AppCompatActivity implements IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
    }

    protected void dismissKeyboard(IBinder windowToken) {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(windowToken, 0);
    }
}
