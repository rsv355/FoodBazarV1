package foodbazar.webmyne.com.foodbazar.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import foodbazar.webmyne.com.foodbazar.MenuListActivity;
import foodbazar.webmyne.com.foodbazar.R;
import foodbazar.webmyne.com.foodbazar.model.AppConstants;
import foodbazar.webmyne.com.foodbazar.model.Hotel;
import foodbazar.webmyne.com.foodbazar.model.HotelsList;
import foodbazar.webmyne.com.foodbazar.model.HotelsMenuList;
import foodbazar.webmyne.com.foodbazar.utils.GetServiceCall;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;


/**
 * Created by jaydeeprana on 01-06-2015.
 */
public class HotelListFragment  extends Fragment {

    public static final String BOOK = "Book";

    HotelsMenuList hotelsMenuList;



    private MyAppAdapter customAdapter;
    private ListView hotelsListView;
    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;
    private ProgressDialog progressDialog;
    private ArrayList<Hotel> hotelArrayList;

    String imagePath;

    String tax;
    String deliveryFee;
    String deliveryTime;

    SharedPreferences sharedpreferences;

    public static final String MyPREFERENCES = "MyPrefs" ;

    public static final String KEY_POSITION = "positionKey";

        int pos;

    public static HotelListFragment newInstance() {

        HotelListFragment contentFragment = new HotelListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), android.R.drawable.screen_background_light_transparent);
        contentFragment.setArguments(bundle);

        return contentFragment;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    private void getHotelList() {



        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        new GetServiceCall(AppConstants.GET_HOTELS+ PrefUtils.getArea(getActivity()),GetServiceCall.TYPE_JSONOBJECT){

            @Override
            public void response(String response) {

                Log.e("response:", response + "");
                progressDialog.dismiss();

                HotelsList hotelsList = new GsonBuilder().create().fromJson(response, HotelsList.class);

                PrefUtils.setHotelsList(hotelsList, getActivity());

                hotelArrayList=hotelsList.hotelArrayList;
                Log.e("list size", hotelArrayList.size() + "");

                customAdapter=new MyAppAdapter(hotelArrayList,getActivity());
                hotelsListView.setAdapter(customAdapter);

            }

            @Override
            public void error(VolleyError error) {

                progressDialog.dismiss();
            }
        }.call();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hotels, container, false);
        hotelsListView= (ListView) rootView.findViewById(R.id.hotelsList);
        getHotelList();
        return rootView;
    }



    private class MyAppAdapter extends BaseAdapter{

        // Holder Class
        public class ViewHolder {
            public TextView name,minimum,deliveryTime,pickupTime,mobile,address;
            public ImageView image;

        }


        public List<Hotel> parkingList;
        public Context context;

        public MyAppAdapter(List<Hotel> parkingList, Context context) {
            this.parkingList = parkingList;
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

                LayoutInflater inflater = getActivity().getLayoutInflater();
                rowView = inflater.inflate(R.layout.item_hotel_list, null);

                // configure view holder
                viewHolder = new ViewHolder();

                viewHolder.name = (TextView) rowView.findViewById(R.id.name);
                viewHolder.address = (TextView) rowView.findViewById(R.id.address);
             //   viewHolder.mobile = (TextView) rowView.findViewById(R.id.mobile);
                viewHolder.minimum = (TextView) rowView.findViewById(R.id.minimumOrder);
                viewHolder.deliveryTime = (TextView) rowView.findViewById(R.id.deliveryTime);
                viewHolder.pickupTime = (TextView) rowView.findViewById(R.id.pickupTime);
                viewHolder.image = (ImageView) rowView.findViewById(R.id.hotelImage);

                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.name.setText(parkingList.get(position).HotelName+"");
            viewHolder.pickupTime.setText(parkingList.get(position).PickUpTime);
            viewHolder.deliveryTime.setText(parkingList.get(position).DeliveryTime);

            deliveryTime = parkingList.get(position).DeliveryTime;

          //  viewHolder.delivery.setText(parkingList.get(position).EmailId+"");
            viewHolder.minimum.setText(parkingList.get(position).MinDeliveryAmount + "");
            viewHolder.address.setText(parkingList.get(position).StreetAddress+"");

            PrefUtils.setPosition(position, getActivity());

//            sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//
//            SharedPreferences.Editor editor = sharedpreferences.edit();
//
//            editor.putInt(KEY_POSITION, position);
//
//            editor.commit();
//
//            pos =     sharedpreferences.getInt(KEY_POSITION, 0);
//
//            Log.e("JAYDEEP POSITION :", ""+pos);

            tax = parkingList.get(position).VatTax;
            deliveryFee = parkingList.get(position).DeliveryFee;

            Log.e("image path:", AppConstants.IMAGE_PATH + parkingList.get(position).LogoPath + parkingList.get(position).Logo + "");

            imagePath = AppConstants.IMAGE_PATH + parkingList.get(position).LogoPath + parkingList.get(position).Logo;

            Log.e("Jaydeep path:", ""+ imagePath);

            // For Image Compress
            Glide.with(getActivity())
                    .load(AppConstants.IMAGE_PATH + parkingList.get(position).LogoPath + parkingList.get(position).Logo)
                    .centerCrop()
                    .into(viewHolder.image);

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   getHotelsMenu(parkingList.get(position).HotelName.toString(),parkingList.get(position).HotelId+"");

                }

                
            });

            return rowView;
        }


    }

    private void getHotelsMenu( final String name, String id) {

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        new  GetServiceCall(AppConstants.GET_HOTELS_MENU+id, GetServiceCall.TYPE_JSONOBJECT){

            @Override
            public void response(String response) {

                Log.e("response:", response + "");
                progressDialog.dismiss();

                hotelsMenuList = new GsonBuilder().create().fromJson(response, HotelsMenuList.class);
                PrefUtils.setHotelsMenu(hotelsMenuList, getActivity());
                PrefUtils.clearCart(getActivity());




                Intent it = new Intent(getActivity(), MenuListActivity.class);

                   it.putExtra("hotel_name", name+"");
                it.putExtra("hotel_path", imagePath+"");
                it.putExtra("tax", tax+"");
                it.putExtra("fee", deliveryFee+"");
                it.putExtra("deliveryTime", deliveryTime+"");


                startActivity(it);

            }

            @Override
            public void error(VolleyError error) {

                progressDialog.dismiss();

            }
        }.call();
    }


    public String getTax() {
        return tax;
    }



}
