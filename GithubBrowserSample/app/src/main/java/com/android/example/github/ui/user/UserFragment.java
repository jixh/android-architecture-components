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

package com.android.example.github.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.android.example.github.R;
import com.android.example.github.databinding.UserFragmentBinding;
import com.android.example.github.ui.DataBindFragment;
import com.android.example.github.ui.common.NavigationController;
import com.jktaihe.library.utils.AutoClearedValue;

import javax.inject.Inject;

public class UserFragment extends DataBindFragment<UserViewModel,UserFragmentBinding> {

    private static final String LOGIN_KEY = "login";

    @Inject
    NavigationController navigationController;

    private AutoClearedValue<RepoListAdapter> adapter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.get().setRetryCallback(()->viewModel.retry());

        viewModel.setLogin(getArguments().getString(LOGIN_KEY));
        viewModel.getUser().observe(this, userResource -> {
            binding.get().setUser(userResource == null ? null : userResource.data);
            binding.get().setUserResource(userResource);
            // this is only necessary because espresso cannot read data binding callbacks.
            binding.get().executePendingBindings();
        });

        RepoListAdapter rvAdapter = new RepoListAdapter(dataBindingComponent, false,
                repo -> navigationController.navigateToRepo(repo.owner.login, repo.name));
        binding.get().repoList.setAdapter(rvAdapter);
        this.adapter = new AutoClearedValue<>(this, rvAdapter);

        initRepoList();

    }


    private void initRepoList() {
        viewModel.getRepositories().observe(this, repos -> {
            // no null checks for adapter.get() since LiveData guarantees that we'll not receive
            // the event if fragment is now show.
            if (repos == null) {
                adapter.get().replace(null);
            } else {
                adapter.get().replace(repos.data);
            }
        });
    }


    @Override
    public int layoutId() {
        return R.layout.user_fragment;
    }

    public static UserFragment create(String login) {
        UserFragment userFragment = new UserFragment();
        Bundle bundle = new Bundle();
        bundle.putString(LOGIN_KEY, login);
        userFragment.setArguments(bundle);
        return userFragment;
    }
}
