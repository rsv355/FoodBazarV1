package foodbazar.webmyne.com.foodbazar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import foodbazar.webmyne.com.foodbazar.model.AppConstants;
import foodbazar.webmyne.com.foodbazar.model.History;
import foodbazar.webmyne.com.foodbazar.model.LoginClass;
import foodbazar.webmyne.com.foodbazar.model.UserOrder;
import foodbazar.webmyne.com.foodbazar.utils.GetServiceCall;
import foodbazar.webmyne.com.foodbazar.utils.PostServiceCall;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;

public class OrderActivity extends ActionBarActivity {


   private TextView firstName;
    private  TextView LastName;
    private    TextView orderId;
    private TextView hotelName;
    private TextView priceToPay;
    private TextView tax;
    private  TextView totalPrice;

    History history;

    History history1;

    private String URL = "http://ws-srv-net.in.webmyne.com/Applications/FoodiesWS/User.svc/json/CheckOutOrderedList/1";

    private ProgressDialog pDialog;

    String jaydeep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);

        firstName = (TextView) findViewById(R.id.firstName);
        LastName = (TextView) findViewById(R.id.LastName);
        orderId = (TextView) findViewById(R.id.orderId);
        hotelName = (TextView) findViewById(R.id.orderStatus);
        priceToPay = (TextView) findViewById(R.id.priceToPay);
        tax = (TextView) findViewById(R.id.tax);
        totalPrice = (TextView) findViewById(R.id.totalPrice);


        pDialog = new ProgressDialog(OrderActivity.this);

        pDialog.setTitle("Progress");

        pDialog.setMessage("Please wait");

        pDialog.setCancelable(false);

        pDialog.show();

        new GetServiceCall(AppConstants.USER_HISTORY+PrefUtils.getLogin(OrderActivity.this).OwnerId+"" , GetServiceCall.TYPE_JSONOBJECT) {

            @Override
            public void response(String response) {

                pDialog.dismiss();

                history = new GsonBuilder().create().fromJson(response, History.class);

                Log.e("Response", "" + response);


                   PrefUtils.setHistory(history, OrderActivity.this);


               history1 = PrefUtils.getHistory(OrderActivity.this);


                    firstName.setText(history1.UserOrderArrayList.get(0).CustomerFirstName);
                    LastName.setText(history1.UserOrderArrayList.get(0).CustomerLastName);
                    orderId.setText(history1.UserOrderArrayList.get(0).OrderId);
                    hotelName.setText(history1.UserOrderArrayList.get(0).HotelName);
                    priceToPay.setText(history1.UserOrderArrayList.get(0).PriceToPay);
                    tax.setText(history1.UserOrderArrayList.get(0).Tax);
                    totalPrice.setText(history1.UserOrderArrayList.get(0).TotalPrice);


                //    jaydeep = order1.CustomerFirstName;

           //     Log.e("My First Name is : ","" +order);

             //   String firstName = order1.CustomerFirstName;

                //     String namee =    m2Item.getBeanMenuItem().get(2).getBeanCategory().get(3).getBeanCuisineObj().getCuisineName();

                //       Log.e("NAME" , "" + namee);

            }

            @Override
            public void error(VolleyError error) {
                pDialog.dismiss();
            }


        }.call();


    }

}
