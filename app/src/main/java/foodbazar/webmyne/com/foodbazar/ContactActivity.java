package foodbazar.webmyne.com.foodbazar;

import android.app.Activity;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import foodbazar.webmyne.com.foodbazar.fragments.ContactFragment;
import github.chenupt.springindicator.SpringIndicator;


public class ContactActivity extends ActionBarActivity {

    private Toolbar toolbar;


    public ViewPager viewPager;


    private MyPagerAdapterrr adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        setToolbar();

        viewPager = (ViewPager) findViewById(R.id.view_pager);



        adapter = new MyPagerAdapterrr(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


    }


        public class MyPagerAdapterrr extends FragmentStatePagerAdapter {

        private final String[] TITLES = {"Our Location"};

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

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Contact Us");
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
