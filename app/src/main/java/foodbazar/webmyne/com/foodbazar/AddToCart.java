package foodbazar.webmyne.com.foodbazar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import foodbazar.webmyne.com.foodbazar.model.FoodDiatList;
import foodbazar.webmyne.com.foodbazar.model.HotelMenuItem;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;

/**
 * Created by jaydeeprana on 08-06-2015.
 */
public class AddToCart extends Activity {

    private HotelMenuItem hotelMenuItem;

  //  private Spinner foodDiet;

//    private FoodDietSpinnerAdapter foodDietSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        // Get Menu Items from PrefUtils Class
        hotelMenuItem = PrefUtils.getMenuItemDetail(AddToCart.this);

//        foodDietSpinnerAdapter = new FoodDietSpinnerAdapter(AddToCart.this, hotelMenuItem.foodDiatListArrayList);
//        foodDiet = (Spinner)findViewById(R.id.spinnerFoodDietType);
//        foodDiet.setAdapter(foodDietSpinnerAdapter);

    }


    private class FoodDietSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

        private Context context;
        private ArrayList<FoodDiatList> asr;

        public FoodDietSpinnerAdapter(Context context, ArrayList<FoodDiatList> asr) {
            this.context = context;
            this.asr = asr;
        }

        @Override
        public int getCount() {
            return asr.size();
        }

        @Override
        public Object getItem(int position) {
            return asr.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {

            TextView txt = new TextView(AddToCart.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr.get(position).DietName);
            txt.setTextColor(Color.parseColor("#000000"));
            return txt;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView txt = new TextView(AddToCart.this);
            txt.setGravity(Gravity.CENTER_VERTICAL);
               txt.setPadding(12, 12, 12, 12);
            txt.setTextSize(18);
          //  txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
            txt.setText(asr.get(position).DietName);
            txt.setTextColor(Color.parseColor("#000000"));
            return txt;
        }
    }
}
