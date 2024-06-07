package com.boxed.irpof.activity.main;

import android.os.Bundle;

import com.boxed.irpof.R;
import com.boxed.irpof.adapters.MainViewPagerAdapter;
import com.boxed.irpof.tabFragment.circulars.CircularsFragment;
import com.boxed.irpof.tabFragment.ditsPanel.DitsPanelFragment;
import com.boxed.irpof.tabFragment.events.EventsFragment;
import com.boxed.irpof.tabFragment.home.HomeFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.boxed.irpof.databinding.ActivityMainBinding;
import com.boxed.irpof.tabFragment.irms.IRMS_Fragment;
import com.boxed.irpof.tabFragment.irpobf.IRPOBF_Fragment;
import com.boxed.irpof.tabFragment.links.LinksFragment;
import com.boxed.irpof.tabFragment.newsArticle.NewsArticleFragment;
import com.boxed.irpof.tabFragment.organization.OrganizationFragment;
import com.boxed.irpof.tabFragment.seniority.SeniorityFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private MainViewPagerAdapter pagerAdapter;

    /////////////////////////////////////
    // LIST OF FRAGMENT OF MAIN TAB  //
    /////////////////////////////////////

    private List<String> itemList;
    private List<Fragment> tabFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tabLayout = binding.mainTabLayout;
        viewPager2 = binding.viewPagerMainTab;

        // getting the list of tab name and passing
        String[] items = getResources().getStringArray(R.array.mainTabItemsName);
        itemList = Arrays.asList(items);
        
        CreateTabFragmentList();
        
        pagerAdapter =  new MainViewPagerAdapter(this, tabFragments);
        viewPager2.setAdapter( pagerAdapter );
        
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(itemList.get(position));
            }
        }).attach();
    }

    private void CreateTabFragmentList() {

        tabFragments  = new ArrayList<>();
        tabFragments.add( new HomeFragment());
        tabFragments.add( new OrganizationFragment());
        tabFragments.add( new DitsPanelFragment());
        tabFragments.add( new IRMS_Fragment());
        tabFragments.add( new EventsFragment());
        tabFragments.add( new SeniorityFragment());
        tabFragments.add( new CircularsFragment());
        tabFragments.add( new NewsArticleFragment());
        tabFragments.add( new IRPOBF_Fragment());
        tabFragments.add( new LinksFragment());

    }

}