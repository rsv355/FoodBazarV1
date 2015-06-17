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

import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import foodbazar.webmyne.com.foodbazar.model.AppConstants;
import foodbazar.webmyne.com.foodbazar.model.HotelsList;
import foodbazar.webmyne.com.foodbazar.model.LoginClass;
import foodbazar.webmyne.com.foodbazar.utils.PostServiceCall;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;

public class SignUpActivity extends ActionBarActivity {

    private Toolbar toolbar;

    HotelsList hotelList;

    private ProgressDialog progressDialog;
    private TextView btnSignUp;
    private EditText etName,etLastName,etEmail,etPassword,etMobile,etAddress,etZip;
 //   private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        hotelList = PrefUtils.getHotelsList(SignUpActivity.this);

       int jd =  hotelList.hotelArrayList.get(PrefUtils.getPosition(SignUpActivity.this)).City;

        String jd1 = hotelList.hotelArrayList.get(PrefUtils.getPosition(SignUpActivity.this)).AreaName;



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.mipmap.ic_navigation_arrow_back);
            toolbar.setTitle("Sign Up");
            setSupportActionBar(toolbar);


        }


        etName = (EditText)findViewById(R.id.etFirstName);
        etLastName = (EditText)findViewById(R.id.etLastName);
        etEmail= (EditText) findViewById(R.id.etEmail);
        etPassword= (EditText) findViewById(R.id.etPassword);
        etMobile= (EditText) findViewById(R.id.etMobile);
        etAddress = (EditText)findViewById(R.id.etAddress);
        etZip = (EditText)findViewById(R.id.etZip);

        btnSignUp= (TextView) findViewById(R.id.btnSignUp);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                callSignUpService();

            }
        });

//        etEmail.setText("nirav@gmail.com");
//        etPassword.setText("123456");
     //   setToolbar();


         }
//    private void setToolbar() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//
//            toolbar.setTitle(getIntent().getStringExtra("hotel_name"));
//            toolbar.setSubtitle(getIntent().getStringExtra("hotel_category"));
//            setSupportActionBar(toolbar);
//            toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
//            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//        }
 //   }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_login, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




//    {
//        "Address":"String",
//            "City":1,
//            "CityName":"String",
//            "Country":1,
//            "DOB":"12/12/2012",
//            "DeviceId":"String",
//            "DeviceType":"String",
//            "EmailId":"Strinfgg@f.fdd",
//            "FirstName":"String",
//            "IsActive":true,
//            "IsDeleted":true,
//            "LastName":"String",
//            "LoginType":1,
//            "MobileNo":"32568942154",
//            "Password":"String",
//            "ProfilePic":"",
//            "ProfilePicFolderName":"",
//            "ResponseId":"String",
//            "ResponseMsg":"String",
//            "RoleId":3,
//            "State":1,
//            "StateName":"",
//            "UpdateBy":0,
//            "UserId":0,
//            "Zip":"Strin"
//    }




    private void callSignUpService() {
        progressDialog=new ProgressDialog(SignUpActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        JSONObject object=new JSONObject();
        try {
            object.put("Address",etAddress.getText().toString().trim());
            object.put("City",hotelList.hotelArrayList.get(PrefUtils.getPosition(SignUpActivity.this)).City);
            object.put("CityName",hotelList.hotelArrayList.get(PrefUtils.getPosition(SignUpActivity.this)).CityName);
            object.put("Country",1);
            object.put("DOB","12/10/1991");
            object.put("DeviceId",etAddress.getText().toString().trim());
            object.put("DeviceType",etAddress.getText().toString().trim());
            object.put("EmailId",etEmail.getText().toString().trim());
            object.put("FirstName",etName.getText().toString().trim());
            object.put("LastName",etLastName.getText().toString().trim());
            object.put("LoginType",1);
            object.put("MobileNo",etMobile.getText().toString().trim());


            object.put("Password",etPassword.getText().toString().trim());
            object.put("ProfilePic",etEmail.getText().toString().trim());
            object.put("ProfilePicFolderName",etEmail.getText().toString().trim());
            object.put("ResponseId",etEmail.getText().toString().trim());
            object.put("ResponseMsg",etEmail.getText().toString().trim());
            object.put("RoleId",3);
            object.put("State",1);
            object.put("StateName",hotelList.hotelArrayList.get(PrefUtils.getPosition(SignUpActivity.this)).State);
            object.put("UpdateBy",1);
            object.put("UserId",0);
            object.put("Zip",etZip.getText().toString().trim());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("OBJECTS" , "" + object);

        new PostServiceCall(AppConstants.SIGNUP,object){

            @Override
            public void response(String response) {
                progressDialog.dismiss();


                Log.e("RESPONSE OF POST :", ""+ response);

//                LoginClass loginClass= new GsonBuilder().create().fromJson(response, LoginClass.class);
//                PrefUtils.setLogin(loginClass, SignUpActivity.this);
//                Log.e("login response:", loginClass.OwnerId + "");
                finish();
            }

            @Override
            public void error(String error) {
                Log.e("login response:", error + "");
            }
        }.call();

    }

}
