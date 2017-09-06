package com.android.example.github.util;

import com.jktaihe.library.api.ApiResponse;
import org.reactivestreams.Publisher;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by jktaihe on 4/9/17.
 * blog: blog.jktaihe.com
 */

public class RxResultHelper {

    public static <T> ObservableTransformer<ApiResponse<T>, T> handleResult() {

        return new ObservableTransformer<ApiResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ApiResponse<T>> upstream) {
                return upstream
                        .flatMap(new Function<ApiResponse<T>, ObservableSource<T>>() {
                            @Override
                            public ObservableSource<T> apply(ApiResponse<T> t) throws Exception {

                                if (t.isSuccessful()){
                                    return Observable.just(t.body);
                                }

                                if (t.code == 2){

                                }

                                return Observable.empty();
                            }
                        }
                );
            }
        };
    }


    public static <T> FlowableTransformer<ApiResponse<T>, T> FThandleResult() {

        return new FlowableTransformer<ApiResponse<T>, T>() {

            @Override
            public Publisher<T> apply(Flowable<ApiResponse<T>> upstream) {
                return upstream.flatMap(new Function<ApiResponse<T>, Publisher<T>>() {
                    @Override
                    public Publisher<T> apply(ApiResponse<T> t) throws Exception {
                        if (t.isSuccessful()){
                            return Flowable.just(t.body);
                        }

                        if (t.code == 2){

                        }

                        return Flowable.empty();
                    }

                });
            }
            };

    }

}
