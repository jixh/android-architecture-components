package com.jktaihe.library.view;

import android.content.Context;
import android.os.IBinder;
import android.support.v4.app.Fragment;

/**
 * Created by jktaihe on 7/6/17.
 * blog: blog.jktaihe.com
 */

public abstract class BaseFragment extends Fragment implements IView {

    protected BaseActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity){
            activity = (BaseActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    protected void dismissKeyboard(IBinder windowToken) {
        if (activity != null) {
          activity.dismissKeyboard(windowToken);
        }
    }
}
