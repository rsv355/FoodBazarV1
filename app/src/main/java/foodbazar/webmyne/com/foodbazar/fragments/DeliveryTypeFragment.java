package foodbazar.webmyne.com.foodbazar.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import foodbazar.webmyne.com.foodbazar.CartActivity;
import foodbazar.webmyne.com.foodbazar.LoginActivity;
import foodbazar.webmyne.com.foodbazar.MainActivity;
import foodbazar.webmyne.com.foodbazar.R;
import foodbazar.webmyne.com.foodbazar.model.LoginClass;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;
import it.neokree.googlenavigationdrawer.GAccount;


public class DeliveryTypeFragment extends Fragment {

    LoginClass loginClass;

    View convertView;
    private TextView btnUSerDetail;

    public static DeliveryTypeFragment newInstance()
    {
        DeliveryTypeFragment fragment = new DeliveryTypeFragment();

        return fragment;
    }


    // Required empty public constructor
    public DeliveryTypeFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView= inflater.inflate(R.layout.fragment_delivery_type, container, false);

        CartActivity activity = (CartActivity) getActivity();

        String myDataFromActivity = activity.getDeliveryFee();

        Log.e("MY JAYDEEP DELIVERY FEE", ""+ myDataFromActivity);



        loginClass = PrefUtils.getLogin(getActivity());

        btnUSerDetail= (TextView) convertView.findViewById(R.id.btnUSerDetail);
        btnUSerDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                ((CartActivity)getActivity()).setCurrentTab(2);
            }
        });
        return convertView;
    }






}