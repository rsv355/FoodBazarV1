package foodbazar.webmyne.com.foodbazar;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import foodbazar.webmyne.com.foodbazar.model.AppConstants;
import foodbazar.webmyne.com.foodbazar.model.Balance;
import foodbazar.webmyne.com.foodbazar.model.LoginClass;
import foodbazar.webmyne.com.foodbazar.utils.GetServiceCall;
import foodbazar.webmyne.com.foodbazar.utils.PostServiceCall;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;

public class WalletActivity extends ActionBarActivity {

    private Toolbar toolbar;

    private TextView points;

    private ProgressDialog progressDialog;

    String balancee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        setToolbar();


        Typeface fonts = Typeface.createFromAsset(getAssets(), "fonts/otttirial.ttf");

        Shimmer.ShimmerTextView txtFoodBazar = (Shimmer.ShimmerTextView)findViewById(R.id.txt_splash);

        txtFoodBazar.setTypeface(fonts);
        //txtFoodBazar = (TextView)findViewById(R.id.txt_splash);
        txtFoodBazar.setText("CONGRATULATIONS !");
        Shimmer.Shimmer shimmer = new Shimmer.Shimmer();
        shimmer.start(txtFoodBazar);


        points = (TextView)findViewById(R.id.points);

        getBalance();

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {

            toolbar.setTitle("User Wallet");

            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    private void getBalance() {

        if(  PrefUtils.getLogin(WalletActivity.this) !=null){


            progressDialog=new ProgressDialog(WalletActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();


            new GetServiceCall(AppConstants.GET_BALANCE+PrefUtils.getLogin(WalletActivity.this).OwnerId+"",GetServiceCall.TYPE_JSONOBJECT){


                @Override
                public void response(String response) {

                    Log.e("response:", response + "");
                    progressDialog.dismiss();

                    Balance balance = new GsonBuilder().create().fromJson(response, Balance.class);

                    PrefUtils.setBalance(balance, WalletActivity.this);

                    Log.e("user balance", balance.UserBal + "");

                  balancee = balance.UserBal;

                    points.setText(balancee);


                }

                @Override
                public void error(VolleyError error) {

                    progressDialog.dismiss();

                }
            }.call();

        }
    }

}
