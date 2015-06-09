package foodbazar.webmyne.com.foodbazar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import foodbazar.webmyne.com.foodbazar.model.HotelMenuItem;
import foodbazar.webmyne.com.foodbazar.model.HotelsMenu;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;


/**
 * Created by jaydeeprana on 02-06-2015.
 */
public class MenuItemListActivity extends Activity {


    HotelsMenu hotelsMenuList;
    private ListView menuItemListView;
    private ArrayList<HotelMenuItem> hotelMenuItemArrayList;

    private TextView hotelName;
    private ImageView hotelImage;

    private MyAppAdapter myAppAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item_list);

        // Get Hotel Menu
        hotelsMenuList= PrefUtils.getHotelsMenuItems(MenuItemListActivity.this);





        hotelMenuItemArrayList=hotelsMenuList.hotelMenuItemArrayList;
        menuItemListView= (ListView) findViewById(R.id.menuItenList);

        myAppAdapter=new MyAppAdapter(hotelMenuItemArrayList,MenuItemListActivity.this);
        menuItemListView.setAdapter(myAppAdapter);

        hotelName = (TextView) findViewById(R.id.hotelName);
        hotelImage = (ImageView)findViewById(R.id.hotelImage);

        hotelName.setText(getIntent().getStringExtra("hotel_name"));

        Glide.with(MenuItemListActivity.this)
                .load(getIntent().getStringExtra("hotel_path"))
                .placeholder(R.drawable.ic_launcher)
                .centerCrop()
                .into(hotelImage);


    }

    private class MyAppAdapter extends BaseAdapter{


        // Holder Class
        public class ViewHolder {
            public TextView name,tagline,price;
            ImageView image;


        }

        public List<HotelMenuItem> parkingList;
        public Context context;

        public MyAppAdapter(List<HotelMenuItem> apps, Context context) {
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
                rowView = inflater.inflate(R.layout.item_menu_list, null);

                // configure view holder
                viewHolder = new ViewHolder();

                viewHolder.name = (TextView) rowView.findViewById(R.id.name);
                viewHolder.tagline = (TextView) rowView.findViewById(R.id.tagline);
                viewHolder.price = (TextView) rowView.findViewById(R.id.price);
                viewHolder.image=(ImageView)rowView.findViewById(R.id.itemImage);

                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();

        }

            viewHolder.name.setText(parkingList.get(position).ItemaName+"");
            viewHolder.tagline.setText(parkingList.get(position).TagLine+"");
            viewHolder.price.setText(getResources().getString(R.string.rupees)+" "+parkingList.get(position).Price+"");

            Glide.with(MenuItemListActivity.this)
                    .load(AppConstants.IMAGE_PATH + parkingList.get(position).MenuFolderPath + parkingList.get(position).MenuIcon)
                    .placeholder(R.drawable.ic_launcher)
                    .centerCrop()
                    .into(viewHolder.image);

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PrefUtils.setMenuItemDetail(parkingList.get(position), MenuItemListActivity.this);

                    Intent it = new Intent(MenuItemListActivity.this,MenuItemDetailActivity.class);

                    it.putExtra("item_name",parkingList.get(position).ItemaName+"");
                    it.putExtra("tax",getIntent().getStringExtra("tax"));
                    it.putExtra("fee",getIntent().getStringExtra("fee"));

                    startActivity(it);

                }
            });

            return rowView;
        }
    }
}
