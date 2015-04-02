package com.example.tab_second;

import java.util.Random;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBar.OnMenuVisibilityListener;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity {

	private static final String TAG = "MainActivity";
	private ViewPager vp_myViewPager;
	private ActionBar mActionBar;
	private MyPagerAdapter mPagerAdapter;
	
	private String[] addresses = { "first", "second", "third" };
    private Tab[] mTabs = new Tab[addresses.length];
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		vp_myViewPager = (ViewPager) findViewById(R.id.vp_myviewpager);
		mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		vp_myViewPager.setAdapter(mPagerAdapter);
		vp_myViewPager.setOnPageChangeListener(mPageChangeListener);
		
		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		
		for(int i =0 ;i!=addresses.length;i++){
			mTabs[i] = mActionBar.newTab().setText(addresses[i]).setTabListener(mTabListener);
			mActionBar.addTab(mTabs[i]);
		}
		
	}
	
	private OnPageChangeListener mPageChangeListener = new OnPageChangeListener() {

        @Override
        public void onPageSelected(int arg0)
        {
            mActionBar.setSelectedNavigationItem(arg0);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2)
        {

        }

        @Override
        public void onPageScrollStateChanged(int arg0)
        {

        }
    };
	
	 private TabListener mTabListener = new TabListener() {
			@Override
			public void onTabSelected(Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onTabUnselected(Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub
				if (tab == mTabs[0])
	            {
	            	vp_myViewPager.setCurrentItem(0);
	            } else if (tab == mTabs[1])
	            {
	            	vp_myViewPager.setCurrentItem(1);
	            } else if (tab == mTabs[2])
	            {
	            	vp_myViewPager.setCurrentItem(2);
	            }
			}
			@Override
			public void onTabReselected(Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
	    };
	
	public class MyPagerAdapter extends FragmentPagerAdapter{

		public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return MyFragment.create(addresses[arg0]);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return addresses.length;
		}
		
	}
	
	public static class MyFragment extends Fragment{
		public static MyFragment create(String address) {
			MyFragment fragment = new MyFragment();
			Bundle bundle = new Bundle();
			bundle.putString("address", address);
			fragment.setArguments(bundle);
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater,
				@Nullable ViewGroup container,
				@Nullable Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			Random random = new Random(System.currentTimeMillis());
			
			Bundle bundle = getArguments();
			
			View v = inflater.inflate(R.layout.tab, null);
			
			
			v.setBackgroundColor(random.nextInt() >> 8 | 0xFF << 24);
            TextView txvAddress = (TextView) v.findViewById(R.id.textView1);
            txvAddress.setTextColor(random.nextInt() >> 8 | 0xFF << 24);
            txvAddress.setBackgroundColor(random.nextInt() >> 8 | 0xFF << 24);
            txvAddress.setText(bundle.getString("address", ""));
            return v;
		}
		
		
	}

}
