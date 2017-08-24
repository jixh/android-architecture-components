/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.example.github.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.android.example.github.R;
import com.android.example.github.databinding.SearchFragmentBinding;
import com.android.example.github.ui.DataBindFragment;
import com.android.example.github.ui.common.NavigationController;
import com.android.example.github.ui.common.RepoListAdapter;
import com.jktaihe.library.utils.AutoClearedValue;
import com.jktaihe.library.utils.ToastUtils;
import com.jktaihe.library.vo.Status;
import com.jktaihe.library.widget.loadmore.wrapper.LoadMoreWrapper;

import javax.inject.Inject;

public class SearchFragment extends DataBindFragment<SearchViewModel, SearchFragmentBinding> {
    @Inject
    NavigationController navigationController;
    AutoClearedValue<LoadMoreWrapper> adapter;

    View.OnClickListener retry = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewModel.refresh();
        }
    };

    private void initSearchInputListener() {
        binding.get().input.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(v);
                return true;
            }
            return false;
        });
        binding.get().input.setOnKeyListener((v, keyCode, event) -> {
            if ((event.getAction() == KeyEvent.ACTION_DOWN)
                    && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                doSearch(v);
                return true;
            }
            return false;
        });
    }

    private void doSearch(View v) {

        String query = binding.get().input.getText().toString();

        if (TextUtils.isEmpty(query)){
            ToastUtils.shortToast(activity,"输入不可为空");
            return;
        }
        // Dismiss keyboard
        dismissKeyboard(v.getWindowToken());
        viewModel.setQuery(query);
    }

    private void initRecyclerView() {

        viewModel.getResults().observe(this, result -> {
            switch (result.status){
                case ERROR:
                    binding.get().progressLayout.showFailed(retry);
                    break;
                case LOADING:
                    binding.get().progressLayout.showLoading();
                    break;
                case SUCCESS:
                    binding.get().progressLayout.showContent();
                    ((RepoListAdapter)adapter.get().getmInnerAdapter()).replace(result == null ? null : result.data);
                    adapter.get().setHasMore(true);
                    binding.get().executePendingBindings();
                    break;
            }

        });

        viewModel.getLoadMoreStatus().observe(this, loadingMore -> {

            String error = loadingMore.getErrorMessageIfNotHandled();
            if (error != null) {
                binding.get().progressLayout.showFailed(retry);
                Snackbar.make(binding.get().textInputLayout3, error, Snackbar.LENGTH_LONG).show();
                return;
            }

            adapter.get().setHasMore(false);
            binding.get().executePendingBindings();
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setTitle(getClass().getSimpleName());
        setBack();

        initRecyclerView();
        RepoListAdapter rvAdapter = new RepoListAdapter(dataBindingComponent, true,
                repo -> navigationController.navigateToRepo(repo.owner.login, repo.name));
        LoadMoreWrapper<RepoListAdapter> mLoadMoreWrapper = new LoadMoreWrapper(rvAdapter);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                viewModel.loadNextPage();
            }
        });
        binding.get().repoList.setAdapter(mLoadMoreWrapper);
        adapter = new AutoClearedValue<>(this, mLoadMoreWrapper);

        initSearchInputListener();
    }


    @Override
    public int layoutId() {
        return R.layout.search_fragment;
    }

}
