package foodbazar.webmyne.com.foodbazar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import foodbazar.webmyne.com.foodbazar.CartActivity;
import foodbazar.webmyne.com.foodbazar.MenuListActivity;
import foodbazar.webmyne.com.foodbazar.R;
import foodbazar.webmyne.com.foodbazar.model.HotelsList;
import foodbazar.webmyne.com.foodbazar.model.SubmitOrder;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;


public class OrderHistoryDetailFragment extends Fragment {

    View convertView;
    private LinearLayout addOrderLayout;
    private SubmitOrder submitOrder;
    private View itemView;
    private TextView btnDeliveryType,totalBottom,tax,subtotal,taxPercent;
    private TextView addProduct;
    public static Double dTax=0.0d,dSubTotal=0.0d,dTotalBottom=0.0d;

    String VatTax;

    double taxx;

    HotelsList hotelList;


    public static OrderHistoryDetailFragment newInstance()
    {
        OrderHistoryDetailFragment fragment = new OrderHistoryDetailFragment();


        return fragment;
    }

    // Required empty public constructor
    public OrderHistoryDetailFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        convertView= inflater.inflate(R.layout.fragment_order_history, container, false);

        CartActivity activity = (CartActivity) getActivity();

//        String myDataFromActivity = activity.getMyData();
//
//        Log.e("JAYDEEP TAX IS :", "" +myDataFromActivity);

        addProduct = (TextView)convertView.findViewById(R.id.addProduct);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(getActivity(), MenuListActivity.class);
                startActivity(it);

            }
        });

        btnDeliveryType= (TextView) convertView.findViewById(R.id.btnDeliveryType);
        totalBottom= (TextView) convertView.findViewById(R.id.mobileNo);
        tax= (TextView) convertView.findViewById(R.id.tax);
        subtotal= (TextView) convertView.findViewById(R.id.customerName);
        taxPercent = (TextView)convertView.findViewById(R.id.emailId);

        hotelList = PrefUtils.getHotelsList(getActivity());

        VatTax = hotelList.hotelArrayList.get(PrefUtils.getPosition(getActivity())).VatTax;

        taxPercent.setText(VatTax);

      //  taxPercent.setText(myDataFromActivity);

        addOrderLayout= (LinearLayout) convertView.findViewById(R.id.addOrderLayout);
        submitOrder= PrefUtils.getCartItems(getActivity());
        dSubTotal=0.0d;

        for(int i=0; i<submitOrder.orderItemArrayList.size();i++) {


            // Item Name (Layout) Inflate on view
            itemView = getActivity().getLayoutInflater().inflate(R.layout.single_order_history_view, addOrderLayout, false);

            // item name and price is added
            TextView itemName= (TextView) itemView.findViewById(R.id.orderId);
            TextView itemTaste = (TextView)itemView.findViewById(R.id.orderHotelName);
            TextView itemPrice= (TextView) itemView.findViewById(R.id.orderPrice);
            itemName.setText(submitOrder.orderItemArrayList.get(i).MenuItemName + " (" + submitOrder.orderItemArrayList.get(i).MenuItemQuantity + ") ");
            itemTaste.setText(submitOrder.orderItemArrayList.get(i).MenuItemTaste);
            itemPrice.setText(getResources().getString(R.string.rupees) + " " + submitOrder.orderItemArrayList.get(i).ItemPrice);

           // view added on linear layout
            addOrderLayout.addView(itemView);

            dSubTotal=dSubTotal+ Double.parseDouble(submitOrder.orderItemArrayList.get(i).ItemPrice);

        //    float taxPercentRupee =  Float.parseFloat(taxPercent.getText().toString());

            taxx = Double.parseDouble(VatTax);

                    dTax=((dSubTotal*taxx)/100);

            dTotalBottom=dSubTotal+dTax;
        }

      //  ((CartActivity)getActivity()).setTotalPrice(dTotalBottom);
        totalBottom.setText(getResources().getString(R.string.rupees) + " " + dTotalBottom + "");
        tax.setText(getResources().getString(R.string.rupees) + " " + dTax + "");
        subtotal.setText(getResources().getString(R.string.rupees) + " " + dSubTotal + "");
        submitOrder.PriceToPay=dSubTotal+"";
        submitOrder.Tax=dTax+"";
        submitOrder.TotalPrice=dTotalBottom+"";
        PrefUtils.AddItemToCart(submitOrder,getActivity());

        btnDeliveryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent it = new Intent(getActivity(), ContactActivity.class);
//                startActivity(it);

                ((CartActivity)getActivity()).setCurrentTab(1);
            }
        });

        return convertView;
    }





}