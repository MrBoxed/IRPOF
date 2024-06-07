package com.boxed.irpof.tabFragment.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.boxed.irpof.R;
import com.boxed.irpof.databinding.FragmentHomeBinding;
import com.boxed.irpof.adapters.ViewPagerAdapter;
import com.boxed.irpof.adapters.ImageSliderAdapter;
import com.boxed.irpof.extra.EnhancedWrapContentViewPager;
import com.boxed.irpof.tabFragment.home.tabs.Fragment_Images;
import com.boxed.irpof.tabFragment.home.tabs.Fragment_Mission_and_Vision;
import com.boxed.irpof.tabFragment.home.tabs.Fragment_RecentEvents;
import com.boxed.irpof.tabFragment.home.tabs.Fragment_Videos;
import com.boxed.irpof.tabFragment.home.tabs.Fragment_WhoWeAre_IRPOF;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<Integer> drawablesList;
    private List<Fragment> fragmentList;
    private TableLayout homeTabLayout;
    private ViewPager2 homeViewPager;
    private EnhancedWrapContentViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // setting the image sliding panel
        SetImageSlider();

        // getting the references
        TextView marqueHeadlineText = binding.marqueeHeadline;
        marqueHeadlineText.setSelected(true);


        TabLayout homeTabLayout = binding.homeTabLayout;
        homeViewPager = binding.homeTabViewPager;
//        viewPager    = binding.homeTabViewPager;

        SetFragmentList(); // setting the list of fragment to pass to the adapter
        ViewPagerAdapter homeViewPagerAdapter = new ViewPagerAdapter(requireActivity(),fragmentList );
        homeViewPager.setAdapter( homeViewPagerAdapter );

        List<String> tabItemString = Arrays.asList(getContext().getResources().getStringArray(R.array.homeTabString));

        new TabLayoutMediator(homeTabLayout, homeViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText( tabItemString.get( position ));
            }
        }).attach();






        return root;
    }

    private void SetImageSlider(){

        drawablesList = new ArrayList<>();
        drawablesList.add(R.drawable.homeimage1 );
        drawablesList.add(R.drawable.homeimage2 );
        drawablesList.add(R.drawable.homeimage3 );

        SliderView sliderView = binding.imageSlider;


        ImageSliderAdapter adapter = new ImageSliderAdapter(requireContext(), drawablesList);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

    private void SetFragmentList() {
        fragmentList = new ArrayList<>();
        fragmentList.add( new Fragment_WhoWeAre_IRPOF());
        fragmentList.add( new Fragment_Mission_and_Vision());
        fragmentList.add( new Fragment_RecentEvents());
        fragmentList.add( new Fragment_Images());
        fragmentList.add( new Fragment_Videos());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}