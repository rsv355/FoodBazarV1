package foodbazar.webmyne.com.foodbazar.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;
import foodbazar.webmyne.com.foodbazar.CartActivity;
import foodbazar.webmyne.com.foodbazar.ContactActivity;
import foodbazar.webmyne.com.foodbazar.HomeScreen;
import foodbazar.webmyne.com.foodbazar.LoginActivity;
import foodbazar.webmyne.com.foodbazar.MenuListActivity;
import foodbazar.webmyne.com.foodbazar.R;
import foodbazar.webmyne.com.foodbazar.model.HotelsList;
import foodbazar.webmyne.com.foodbazar.model.SubmitOrder;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;


public class CartDetailFragment extends Fragment {

    View convertView;
    private LinearLayout addOrderLayout;
    private SubmitOrder submitOrder;
    private View itemView;
    private TextView btnDeliveryType,totalBottom,tax,subtotal,taxPercent;
    private TextView addProduct;
    private TextView clearcart;
    ListView cartItemList;
    int quan;
    Double  price;
    int i;
    View footer;
    TextView itemPrice;
    TextView itemQuantity;
    MyAdapter adp;

    public static Double dTax=0.0d,dSubTotal=0.0d,dTotalBottom=0.0d;

    String VatTax;

    double taxx;

    HotelsList hotelList;


    public static CartDetailFragment newInstance()
    {
        CartDetailFragment fragment = new CartDetailFragment();


        return fragment;
    }

    // Required empty public constructor
    public CartDetailFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        convertView= inflater.inflate(R.layout.fragment_cart_detail, container, false);

        CartActivity activity = (CartActivity) getActivity();

//        String myDataFromActivity = activity.getMyData();
//
//        Log.e("JAYDEEP TAX IS :", "" +myDataFromActivity);

        addProduct = (TextView)convertView.findViewById(R.id.addProduct);
        cartItemList= (ListView)convertView.findViewById(R.id.cartItemList);


        clearcart = (TextView)convertView.findViewById(R.id.clearCart);

        clearcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PrefUtils.clearCart(getActivity());
                        dialog.dismiss();

                        Intent it = new Intent(getActivity(), HomeScreen.class);
                        startActivity(it);

                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();


                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setMessage("Are you sure you want to Clear Cart?");
                dialog.show();
            }
        });



        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(getActivity(), MenuListActivity.class);
                startActivity(it);

            }
        });

     /*   btnDeliveryType= (TextView) convertView.findViewById(R.id.btnDeliveryType);
        totalBottom= (TextView) convertView.findViewById(R.id.totalBottom);
        tax= (TextView) convertView.findViewById(R.id.tax);
        subtotal= (TextView) convertView.findViewById(R.id.subtotal);
        taxPercent = (TextView)convertView.findViewById(R.id.taxPercent);

        hotelList = PrefUtils.getHotelsList(getActivity());

        VatTax = hotelList.hotelArrayList.get(PrefUtils.getPosition(getActivity())).VatTax;

        taxPercent.setText(VatTax);*/

      //  taxPercent.setText(myDataFromActivity);

       /* addOrderLayout= (LinearLayout) convertView.findViewById(R.id.addOrderLayout);
        submitOrder= PrefUtils.getCartItems(getActivity());
        dSubTotal=0.0d;*/

        submitOrder= PrefUtils.getCartItems(getActivity());
        footer= getActivity().getLayoutInflater().inflate(R.layout.cart_listview_footer, cartItemList, false);
        cartItemList.addFooterView(footer);

        adp = new MyAdapter(getActivity(),submitOrder);
        cartItemList.setAdapter(adp);

        updateTotal1();

/*
        for( i=0; i<submitOrder.orderItemArrayList.size();i++) {


            // Item Name (Layout) Inflate on view
            itemView = getActivity().getLayoutInflater().inflate(R.layout.single_order_item_view, addOrderLayout, false);

            // item name and price is added
            TextView itemName= (TextView) itemView.findViewById(R.id.name);
             itemQuantity= (TextView) itemView.findViewById(R.id.quantity);
            TextView itemDelete= (TextView) itemView.findViewById(R.id.delete);


            itemDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                          //  PrefUtils.clearCart(MenuListActivity.this);

                           addOrderLayout.removeViewAt(i);
                            dialog.dismiss();

                        }
                    });

                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.dismiss();


                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.setMessage("Are you sure you want to delete this item ??");
                    dialog.show();
                }
            });



            ImageView add = (ImageView)itemView.findViewById(R.id.add);
            ImageView remove = (ImageView)itemView.findViewById(R.id.remove);

            TextView itemTaste = (TextView)itemView.findViewById(R.id.taste);
             itemPrice= (TextView) itemView.findViewById(R.id.price);


            price = Double.parseDouble(submitOrder.orderItemArrayList.get(i).ItemPrice);
        quan = Integer.parseInt(submitOrder.orderItemArrayList.get(i).MenuItemQuantity);

            Log.e("QUANTITY :", "" + quan);

            itemName.setText(submitOrder.orderItemArrayList.get(i).MenuItemName);
        //    itemQuantity.setText(submitOrder.orderItemArrayList.get(i).MenuItemQuantity);


       itemQuantity.setText("" + quan);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    itemQuantity.setText("" + (++quan));
                    itemPrice.setText(getResources().getString(R.string.rupees) + " " + (price * quan) + "");

                    price = price * quan;

                }
            });


            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (quan > 1) {

                        itemQuantity.setText("" + (--quan));
                        itemPrice.setText(getResources().getString(R.string.rupees) + " " + (price * quan) + "");

                        price = price * quan;

                         }

                }
            });

            itemPrice.setText(getResources().getString(R.string.rupees) + price);
                itemTaste.setText(submitOrder.orderItemArrayList.get(i).MenuItemTaste);
                //      itemPrice.setText(getResources().getString(R.string.rupees) + " " + submitOrder.orderItemArrayList.get(i).ItemPrice);


                // view added on linear layout
            addOrderLayout.addView(itemView);

                dSubTotal=dSubTotal+ price;

                //    float taxPercentRupee =  Float.parseFloat(taxPercent.getText().toString());

                taxx=Double.parseDouble(VatTax);

                dTax=((dSubTotal*taxx)/100);

                dTotalBottom=dSubTotal+dTax;
            }*/

          /*  ((CartActivity)getActivity()).setTotalPrice(dTotalBottom);
        totalBottom.setText(getResources().getString(R.string.rupees) + " " + dTotalBottom + "");
        tax.setText(getResources().getString(R.string.rupees) + " " + dTax + "");
        subtotal.setText(getResources().getString(R.string.rupees) + " " + dSubTotal + "");
        submitOrder.PriceToPay=dSubTotal+"";
        submitOrder.Tax=dTax+"";
        submitOrder.TotalPrice=dTotalBottom+"";
        PrefUtils.AddItemToCart(submitOrder,getActivity());*/

     /*   btnDeliveryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent it = new Intent(getActivity(), ContactActivity.class);
//                startActivity(it);

                ((CartActivity)getActivity()).setCurrentTab(1);
            }
        });*/

        return convertView;
    }


    public void updateTotal1(){
        TextView subtotal = (TextView)footer.findViewById(R.id.subtotal);
        double total=0.0;
        for(int i=0;i<submitOrder.orderItemArrayList.size();i++){
            total +=Double.parseDouble(submitOrder.orderItemArrayList.get(i).ItemPrice);
        }

        subtotal.setText(""+total);


    }


    class MyAdapter extends BaseAdapter {
        int tempQty;
        private Context context;
        SubmitOrder values;

        MyAdapter(Context ctx,SubmitOrder obj){
            this.context = ctx;
            this.values = obj;
        }

        @Override
        public int getCount() {
            return values.orderItemArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return values.orderItemArrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            View row = inflater.inflate(R.layout.single_order_item_view, parent, false);

            TextView price = (TextView) row.findViewById(R.id.price);
            TextView name = (TextView) row.findViewById(R.id.name);
            final TextView quantity = (TextView) row.findViewById(R.id.quantity);

            ImageView add = (ImageView) row.findViewById(R.id.add);
            ImageView remove = (ImageView) row.findViewById(R.id.remove);

            name.setText(values.orderItemArrayList.get(i).MenuItemName);
            price.setText("" + values.orderItemArrayList.get(i).ItemPrice);
            quantity.setText("" + values.orderItemArrayList.get(i).MenuItemQuantity);



            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("cliecked", "add");

                    tempQty = Integer.parseInt(quantity.getText().toString());
                    tempQty += 1;
                    quantity.setText("" + tempQty);

                    updateTotal(quantity.getText().toString(),values.orderItemArrayList.get(i).ItemPrice,position);
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tempQty <= 1) {
                        tempQty = Integer.parseInt(quantity.getText().toString());
                        tempQty = 1;
                        quantity.setText("" + tempQty);
                    } else {
                        tempQty = Integer.parseInt(quantity.getText().toString());
                        tempQty -= 1;
                        quantity.setText("" + tempQty);
                    }

                    updateTotal(quantity.getText().toString(),values.orderItemArrayList.get(i).ItemPrice,position);
                }
            });

            return row;
        }




        private void updateTotal(String qty,String price,int menuItemPOS){

            try {
                int tempQty = Integer.parseInt(qty);
                double tempPrice = Double.parseDouble(price);


                SubmitOrder tempSub = submitOrder;
                tempSub.orderItemArrayList.get(menuItemPOS).MenuItemQuantity = qty;
                tempSub.orderItemArrayList.get(menuItemPOS).ItemPrice = price;

                //PrefUtils.AddItemToCart(tempSub, context);

                updateTotal1();

                adp.notifyDataSetChanged();
            }catch (Exception e){
                Log.e("exc",e.toString());
        }


        }
    }

}