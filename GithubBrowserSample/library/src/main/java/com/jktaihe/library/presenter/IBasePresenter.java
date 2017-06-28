package com.jktaihe.library.presenter;


import com.jktaihe.library.view.MVPView;

/**
 * Created by jktaihe on 20/6/17.
 * blog: blog.jktaihe.com
 */

public interface IBasePresenter<V extends MVPView> {

    void subscribe(V view);

    void unsubscribe();

}
