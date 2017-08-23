package com.jktaihe.library.view;

import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jktaihe.library.R;

/**
 * Created by jktaihe on 13/6/17.
 * blog: blog.jktaihe.com
 */

public abstract class TitleBarActivity extends BaseActivity implements ITitleView {

    private Toolbar mToolbar;
    private TextView mTitleView;

    @Override
    public void initView() {
        if (toolbarId() != 0) {
            mToolbar = (Toolbar) findViewById(toolbarId());
            mTitleView = (TextView) findViewById(titleViewId());
            setSupportActionBar(mToolbar);
        }
    }

    @Override
    public  int toolbarId() { return 0; }

    @Override
    public  int titleViewId() { return 0; }

    protected void setTitle(@NonNull String title){
        mTitleView.setText(title);
    }

    public void setTitle(@StringRes int titleId){
        mTitleView.setText(titleId);
    }

    protected void setBack(){
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TitleBarActivity.this.onBackPressed();
            }
        });
    }

    /**
     * 设置
     * @param resId
     * @param onMenuItemClickListener
     */
    protected void showRightMenu(@MenuRes int resId, Toolbar.OnMenuItemClickListener onMenuItemClickListener){
        mToolbar.inflateMenu(resId);
        mToolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    protected void showRightMenu(@Nullable String txt, @Nullable int imageId, Toolbar.OnMenuItemClickListener onMenuItemClickListener){
        if (mToolbar.getMenu()==null || mToolbar.getMenu().size()!=1){
            showRightMenu(R.menu.toolbar_menu,onMenuItemClickListener);
        }

        mToolbar.getMenu().getItem(0).setVisible(true);

        if (!TextUtils.isEmpty(txt))
            mToolbar.getMenu().getItem(0).setTitle(txt);
        if (imageId>0)
            mToolbar.getMenu().getItem(0).setIcon(imageId);

    }

    protected void hideRightMenu(){
        if (mToolbar.getMenu()!=null && mToolbar.getMenu().hasVisibleItems())
            mToolbar.getMenu().getItem(0).setVisible(false);
    }
}
