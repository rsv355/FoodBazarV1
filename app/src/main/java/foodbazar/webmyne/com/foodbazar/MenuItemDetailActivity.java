package foodbazar.webmyne.com.foodbazar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import foodbazar.webmyne.com.foodbazar.model.FoodDiatList;
import foodbazar.webmyne.com.foodbazar.model.HotelMenuItem;
import foodbazar.webmyne.com.foodbazar.model.OrderItem;
import foodbazar.webmyne.com.foodbazar.model.SubmitOrder;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;

/**
 * Created by jaydeeprana on 02-06-2015.
 */
public class MenuItemDetailActivity extends Activity {

    int i = 1;
    private HotelMenuItem hotelMenuItem;
    private TextView addToCart;
    private TextView price;
    private ProgressDialog progressDialog;
    private JSONObject object;
    private JSONArray jsonArray;
    private JSONObject innerObject;
    private Spinner spinnerFoodDietType;
    private TextView name, tagline, cuisine, quantity;
    private TextView totalPrice;

    private FoodDietSpinnerAdapter foodDietSpinnerAdapter;
    private ImageView remove, add;
    private SubmitOrder submitOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item_detail1);


        // Get Menu Items from PrefUtils Class
        hotelMenuItem = PrefUtils.getMenuItemDetail(MenuItemDetailActivity.this);

        // For findView by Id
        initView();

        // For Add and minus the quantity
        initData();
    }

    private void initData() {

        quantity.setText("Quantity " + i);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantity.setText("Quantity " + (++i));
                price.setText(getResources().getString(R.string.rupees) + " " + (Integer.parseInt(hotelMenuItem.Price) * i) + "");

                totalPrice.setText(getResources().getString(R.string.rupees) + " " + (Integer.parseInt(hotelMenuItem.Price) * i) + "");
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i > 1) {

                    quantity.setText("Quantity " + (--i));
                    price.setText(getResources().getString(R.string.rupees) + " " + (Integer.parseInt(hotelMenuItem.Price) * i) + "");

                    totalPrice.setText(getResources().getString(R.string.rupees) + " " + (Integer.parseInt(hotelMenuItem.Price) *i) + "");

                }

            }
        });

        price.setText(getResources().getString(R.string.rupees) + " " + hotelMenuItem.Price + "");

        totalPrice.setText(getResources().getString(R.string.rupees) + " " + hotelMenuItem.Price + "");

        foodDietSpinnerAdapter = new FoodDietSpinnerAdapter(MenuItemDetailActivity.this, hotelMenuItem.foodDiatListArrayList);
        spinnerFoodDietType = (Spinner) findViewById(R.id.spinnerFoodDietType);
        spinnerFoodDietType.setAdapter(foodDietSpinnerAdapter);

        name.setText(hotelMenuItem.ItemaName);
        tagline.setText(hotelMenuItem.TagLine);
        // cuisine.setText(hotelMenuItem.cuisineObject.CuisineName);

    }

    private void initView() {

        addToCart = (TextView) findViewById(R.id.addToCart);
        price = (TextView) findViewById(R.id.price);
        quantity = (TextView) findViewById(R.id.quantity);
        remove = (ImageView) findViewById(R.id.remove);
        add = (ImageView) findViewById(R.id.add);
        name = (TextView) findViewById(R.id.name);
        tagline = (TextView) findViewById(R.id.tagline);

        totalPrice = (TextView)findViewById(R.id.totalPrice);
        //  cuisine= (TextView) findViewById(R.id.cuisine);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("food diete id", hotelMenuItem.foodDiatListArrayList.get(spinnerFoodDietType.getSelectedItemPosition()).DietId + "");
                Log.e("item id", hotelMenuItem.ItemId + "");
                Log.e("item quantity", i + "");
                Log.e("delivery area", PrefUtils.getArea(MenuItemDetailActivity.this) + "");
                Log.e("delivery city", "1");

                Log.e("user id", "2");
                Log.e("order by ", "2");
                Log.e("hotel id", PrefUtils.getMenuItemDetail(MenuItemDetailActivity.this).HotelId + "");
                Log.e("price to pay", (Integer.parseInt(hotelMenuItem.Price) * i) + "");

                if (PrefUtils.getCartItems(MenuItemDetailActivity.this) != null) {
                    submitOrder = PrefUtils.getCartItems(MenuItemDetailActivity.this);
                } else {
                    submitOrder = new SubmitOrder();
                }

                ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();

                orderItems.add(new OrderItem(hotelMenuItem.foodDiatListArrayList.get(spinnerFoodDietType.getSelectedItemPosition()).DietId + "", hotelMenuItem.ItemId + "", hotelMenuItem.ItemaName + "", i + "", (Integer.parseInt(hotelMenuItem.Price) * i) + ""));

                if(submitOrder.orderItemArrayList !=null) {
                    submitOrder.orderItemArrayList.add(orderItems.get(0));
                } else {
                    submitOrder.orderItemArrayList=new ArrayList<OrderItem>();
                    submitOrder.orderItemArrayList.add(orderItems.get(0));
                }


                submitOrder.DeliveryCity="1";
                submitOrder.DeliveryCountry="1";
                submitOrder.DeliveryState="1";
                submitOrder.OrderStatus="1";
                submitOrder.DeliveryArea=PrefUtils.getArea(MenuItemDetailActivity.this)+"";
                submitOrder.Userid="2";
                submitOrder.OrderBy="2";
                submitOrder.HotelId=PrefUtils.getMenuItemDetail(MenuItemDetailActivity.this).HotelId+"";

                PrefUtils.AddItemToCart(submitOrder,MenuItemDetailActivity.this);

                Intent intent = new Intent(MenuItemDetailActivity.this, CartActivity.class);

                startActivity(intent);

            }
        });

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
            return position;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {

            TextView txt = new TextView(MenuItemDetailActivity.this);
           txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr.get(position).DietName);
            txt.setTextColor(Color.parseColor("#000000"));
            return txt;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView txt = new TextView(MenuItemDetailActivity.this);
            txt.setGravity(Gravity.CENTER_VERTICAL);
         //   txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
            txt.setText(asr.get(i).DietName);
            txt.setTextColor(Color.parseColor("#000000"));
            return txt;
        }
    }
}
