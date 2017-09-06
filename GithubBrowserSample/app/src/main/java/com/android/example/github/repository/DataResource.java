package com.android.example.github.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.android.example.github.util.RxResultHelper;
import com.android.example.github.util.RxSchedulersHelper;
import com.android.example.github.util.RxSubscriber;
import com.jktaihe.library.api.ApiResponse;
import com.jktaihe.library.vo.Resource;
import org.reactivestreams.Publisher;
import javax.annotation.Nullable;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public abstract class DataResource<ResultType> {


    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();


    DataResource() {

        result.setValue(Resource.loading(null));

        Flowable<ResultType> dbSource = loadFromDb();

        dbSource.compose(RxSchedulersHelper.FT_io_main())
                .subscribe(new RxSubscriber<ResultType>(){
                    @Override
                    public void onNext(ResultType resultType) {
                        if (shouldFetch(resultType)) {
                            fetchFromNetwork();
                        }else {
                            result.setValue(Resource.success(resultType));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        result.setValue(Resource.error(t.getMessage(),null));
                    }
                });
    }

    private void fetchFromNetwork() {

        Flowable<ApiResponse<ResultType>> apiResponse = createCall();
        apiResponse
                .subscribeOn(Schedulers.io())
                .compose(RxResultHelper.FThandleResult())
                .flatMap(new Function<ResultType, Publisher<ResultType>>() {
                    @Override
                    public Publisher<ResultType> apply(ResultType resultType) throws Exception {
                        saveCallResult(resultType);
                        return Flowable.just(resultType);
                    }
                })
                .subscribe(new RxSubscriber<ResultType>(){
                    @Override
                    public void onNext(ResultType resultType) {
                        super.onNext(resultType);
                        result.setValue(Resource.success(resultType));
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        result.setValue(Resource.error(t.getMessage(),null));
                    }
                });
    }


    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }

    protected abstract void saveCallResult(@NonNull ResultType data);

    protected abstract boolean shouldFetch(@Nullable ResultType data);

    @NonNull
    protected abstract Flowable<ResultType> loadFromDb();

    @NonNull
    protected abstract Flowable<ApiResponse<ResultType>> createCall();
}
