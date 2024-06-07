package com.boxed.irpof.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class MainViewPagerAdapter extends FragmentStateAdapter {

    private List<Fragment> mainTabFragmentList;

    public MainViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragments) {
        super(fragmentActivity);

        mainTabFragmentList = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mainTabFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return mainTabFragmentList.size();
    }
}
