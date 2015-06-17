package foodbazar.webmyne.com.foodbazar.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    int quan;
    Double  price;
    int i;

    TextView itemPrice;
    TextView itemQuantity;


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

        btnDeliveryType= (TextView) convertView.findViewById(R.id.btnDeliveryType);
        totalBottom= (TextView) convertView.findViewById(R.id.totalBottom);
        tax= (TextView) convertView.findViewById(R.id.tax);
        subtotal= (TextView) convertView.findViewById(R.id.subtotal);
        taxPercent = (TextView)convertView.findViewById(R.id.taxPercent);

        hotelList = PrefUtils.getHotelsList(getActivity());

        VatTax = hotelList.hotelArrayList.get(PrefUtils.getPosition(getActivity())).VatTax;

        taxPercent.setText(VatTax);

      //  taxPercent.setText(myDataFromActivity);

        addOrderLayout= (LinearLayout) convertView.findViewById(R.id.addOrderLayout);
        submitOrder= PrefUtils.getCartItems(getActivity());
        dSubTotal=0.0d;

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
            }

            ((CartActivity)getActivity()).setTotalPrice(dTotalBottom);
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