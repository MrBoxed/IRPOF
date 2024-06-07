package com.boxed.irpof.tabFragment.ditsPanel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boxed.irpof.R;
import com.boxed.irpof.adapters.ViewPagerAdapter;
import com.boxed.irpof.tabFragment.organization.tabs.ConstituentUnitsFragment;
import com.boxed.irpof.tabFragment.organization.tabs.ConstitutionFragment;
import com.boxed.irpof.tabFragment.organization.tabs.ExecutiveBodyFragment;
import com.boxed.irpof.tabFragment.organization.tabs.IncumbencyBoardFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class DitsPanelFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private List<String> tabItemStringList;
    private List<Fragment> fragmentList;

    public DitsPanelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tabItemStringList = Arrays.asList( requireContext().getResources().getStringArray(R.array.ditsPanelTabString));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_dits_panel, container, false);

        tabLayout = view.findViewById(R.id.ditsPanelTabLayout);
        viewPager = view.findViewById(R.id.ditsPanleViewPager);

        SetFragmentList();

        ViewPagerAdapter adapter = new ViewPagerAdapter(requireActivity(), fragmentList);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText( tabItemStringList.get(position));
            }
        }).attach();

        return  view;
    }

    private void SetFragmentList() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new ConstitutionFragment());
        fragmentList.add(new ExecutiveBodyFragment());
        fragmentList.add(new ConstituentUnitsFragment());
    }
}