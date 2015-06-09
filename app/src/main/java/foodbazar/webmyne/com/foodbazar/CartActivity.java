package foodbazar.webmyne.com.foodbazar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import foodbazar.webmyne.com.foodbazar.fragments.CartDetailFragment;
import foodbazar.webmyne.com.foodbazar.fragments.DeliveryTypeFragment;
import foodbazar.webmyne.com.foodbazar.fragments.PaymentTypeFragment;
import foodbazar.webmyne.com.foodbazar.fragments.UserDetailsFragment;
import foodbazar.webmyne.com.foodbazar.model.SubmitOrder;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;
import github.chenupt.springindicator.SpringIndicator;


public class CartActivity extends ActionBarActivity {

    private Toolbar toolbar;
    public ViewPager viewPager;
   // private SpringIndicator springIndicator;
    public TextView priceView;
    private MyPagerAdapter adapter;
    SubmitOrder submitOrder;

    String tax;
    String fee;

    public void setCurrentTab(int i){
        viewPager.setCurrentItem(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

            tax = getIntent().getStringExtra("tax");
        fee = getIntent().getStringExtra("fee");

        Log.e("VAT TAX IS :", "" + tax);

        Log.e("DELIVERY FEE IS :", "" + fee);

        priceView= (TextView) findViewById(R.id.price);
        submitOrder= PrefUtils.getCartItems(CartActivity.this);
        Log.e("hotel id", submitOrder.HotelId + "");
       //setToolbar();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
      //  springIndicator = (SpringIndicator) findViewById(R.id.indicator);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        // just set viewPager
      //  springIndicator.setViewPager(viewPager);
    }

    public void setTotalPrice(double price){
        priceView.setText(getResources().getString(R.string.rupees)+" "+price+"");
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {

        private final String[] TITLES = {"1","2","3","4"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){

                CartDetailFragment fragment = new CartDetailFragment();

                return CartDetailFragment.newInstance();


            }
            else if(position == 1){
                return DeliveryTypeFragment.newInstance();
           }
 else if(position == 2){
                return UserDetailsFragment.newInstance();
            }
 else{
                return PaymentTypeFragment.newInstance();
            }
        }
    }

//    private void setToolbar() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            toolbar.setTitle("My Cart");
//            setSupportActionBar(toolbar);
//            toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
//            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//        }
//    }


    public String getMyData() {
        return tax;
    }

    public String getDeliveryFee() {
        return fee;
    }

}




