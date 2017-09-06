package com.android.example.github.util;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by jktaihe on 4/9/17.
 * blog: blog.jktaihe.com
 */

public class RxSubscriber<T>  implements Subscriber<T> {

    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();

        String msg;

        if (t instanceof UnknownHostException) {
            msg = "没有网络...";
        } else if (t instanceof SocketTimeoutException) {
            // 超时
            msg = "超时...";
        }else{
            msg = "请求失败，请稍后重试...";
        }

    }

    @Override
    public void onComplete() {

    }
}
