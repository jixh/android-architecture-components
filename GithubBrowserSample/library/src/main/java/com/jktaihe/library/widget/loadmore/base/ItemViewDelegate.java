package com.jktaihe.library.widget.loadmore.base;

import android.view.View;
import com.jktaihe.library.widget.loadmore.base.ViewHolder;

/**
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}
