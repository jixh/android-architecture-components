package com.jktaihe.library.viewmodel;

import android.arch.lifecycle.ViewModel;
import dagger.internal.Factory;
import java.util.Map;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GithubViewModelFactory_Factory implements Factory<GithubViewModelFactory> {
  private final Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> creatorsProvider;

  public GithubViewModelFactory_Factory(
      Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> creatorsProvider) {
    assert creatorsProvider != null;
    this.creatorsProvider = creatorsProvider;
  }

  @Override
  public GithubViewModelFactory get() {
    return new GithubViewModelFactory(creatorsProvider.get());
  }

  public static Factory<GithubViewModelFactory> create(
      Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> creatorsProvider) {
    return new GithubViewModelFactory_Factory(creatorsProvider);
  }
}
