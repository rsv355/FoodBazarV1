package foodbazar.webmyne.com.foodbazar;

import android.app.Activity;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import foodbazar.webmyne.com.foodbazar.fragments.ContactFragment;
import github.chenupt.springindicator.SpringIndicator;


public class ContactActivity extends FragmentActivity {


    public ViewPager viewPager;
    private SpringIndicator springIndicator;

    private MyPagerAdapterrr adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        springIndicator = (SpringIndicator) findViewById(R.id.indicator);


        adapter = new MyPagerAdapterrr(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        // just set viewPager
        springIndicator.setViewPager(viewPager);
    }


        public class MyPagerAdapterrr extends FragmentStatePagerAdapter {

        private final String[] TITLES = {"Our Location","Contact Us"};

        public MyPagerAdapterrr(FragmentManager fm) {
            super(fm);
        }




        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }



        @Override
        public Fragment getItem(int position) {

            if (position == 0){

                return ContactFragment.newInstance();
            }

            else {
                return ContactFragment.newInstance();
            }


        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
}
