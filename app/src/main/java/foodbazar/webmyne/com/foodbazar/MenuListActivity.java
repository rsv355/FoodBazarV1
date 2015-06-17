package foodbazar.webmyne.com.foodbazar;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import foodbazar.webmyne.com.foodbazar.model.AppConstants;
import foodbazar.webmyne.com.foodbazar.model.HotelsMenu;
import foodbazar.webmyne.com.foodbazar.model.HotelsMenuList;
import foodbazar.webmyne.com.foodbazar.model.SubmitOrder;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;


/**
 * Created by jaydeeprana on 01-06-2015.
 */
public class MenuListActivity extends ActionBarActivity {




    private ArrayList<HotelsMenu> hotelsMenuArrayList;


    HotelsMenuList hotelsMenuList;
    private ListView menuListView;

    private MyAppAdapter myAppAdapter;

    private TextView hotelName;
    private TextView deliveryTime;

    private ImageView hotelImage;

    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        setToolbar();




       // Get Menu List
        hotelsMenuList = PrefUtils.getHotelsMenu(MenuListActivity.this);

        hotelsMenuArrayList = hotelsMenuList.hotelsMenuArrayList;
        menuListView= (ListView) findViewById(R.id.menuList);

        myAppAdapter=new MyAppAdapter(hotelsMenuArrayList,MenuListActivity.this);
        menuListView.setAdapter(myAppAdapter);


        hotelName = (TextView) findViewById(R.id.hotelName);
        deliveryTime = (TextView) findViewById(R.id.deliveryTime);
        hotelImage = (ImageView)findViewById(R.id.hotelImage);

        hotelName.setText(getIntent().getStringExtra("hotel_name"));

        deliveryTime.setText(getIntent().getStringExtra("deliveryTime"));


        Glide.with(MenuListActivity.this)
                .load(getIntent().getStringExtra("hotel_path"))
                .placeholder(R.drawable.ic_launcher)
                .centerCrop()
                .into(hotelImage);

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(getIntent().getStringExtra("hotel_name"));
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MenuListActivity.this);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            PrefUtils.clearCart(MenuListActivity.this);
                            dialog.dismiss();
                            finish();

                            Intent it = new Intent(MenuListActivity.this, HomeScreen.class);
                            startActivity(it);

                        }
                    });

                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.dismiss();


                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.setMessage("Are you sure you'd like to change restaurants? Your current order will be lost.");
                    dialog.show();
                }
            });
        }
    }

    private class MyAppAdapter extends BaseAdapter{


        // Holder Class
        public class ViewHolder {
            public TextView name;
            public ImageView image;

        }

        public List<HotelsMenu> parkingList;
        public Context context;


        public MyAppAdapter(List<HotelsMenu> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;
            ViewHolder viewHolder;

            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item_menu_name, null);

                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) rowView.findViewById(R.id.name);
                viewHolder.image= (ImageView) rowView.findViewById(R.id.hotelImage);

                Glide.with(MenuListActivity.this)
                        .load(AppConstants.IMAGE_PATH + parkingList.get(position).CategoryFolderPath + parkingList.get(position).CategoryIcon)
                        .placeholder(R.drawable.ic_launcher)
                        .centerCrop()
                        .into(viewHolder.image);

                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.name.setText(parkingList.get(position).CategoryName+"");

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PrefUtils.setHotelsMenuItems(parkingList.get(position), MenuListActivity.this);

                    Intent it = new Intent(MenuListActivity.this, MenuItemListActivity.class);
                    it.putExtra("hotel_name", getIntent().getStringExtra("hotel_name"));
                    it.putExtra("tax", getIntent().getStringExtra("tax"));
                    it.putExtra("hotel_path", getIntent().getStringExtra("hotel_path"));
                    it.putExtra("tax", getIntent().getStringExtra("tax"));
                    it.putExtra("fee", getIntent().getStringExtra("fee"));
                    it.putExtra("hotel_category",parkingList.get(position).CategoryName+"");
                    it.putExtra("deliveryTime",getIntent().getStringExtra("deliveryTime"));

                    startActivity(it);



                }
            });




                return rowView;
        }
    }
}
